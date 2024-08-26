package org.example.component;

import static org.quartz.TriggerBuilder.newTrigger;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.batch.TicketingAlertBatch;
import org.example.dto.response.TicketingAlertToSchedulerDomainResponse;
import org.example.job.TicketingAlertQuartzJob;
import org.example.service.dto.response.TicketingAlertServiceResponse;
import org.example.usecase.TicketingAlertUseCase;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketingAlertBatchComponent implements TicketingAlertBatch {

    private final Scheduler ticketingAlertscheduler;
    private final TicketingAlertUseCase ticketingAlertUseCase;

    @PostConstruct
    public void initializeJobsAndTriggers() {
        var ticketingAlerts = ticketingAlertUseCase.findAllTicketingAlerts();

        for (TicketingAlertToSchedulerDomainResponse ticketingAlert : ticketingAlerts) {
            reserveTicketingAlerts(TicketingAlertServiceResponse.from(ticketingAlert));
        }
    }

    @Override
    public void reserveTicketingAlerts(TicketingAlertServiceResponse ticketingAlert) {
        try {
            JobKey jobKey = getJobKey(ticketingAlert);
            boolean jobExists = ticketingAlertscheduler.checkExists(jobKey);

            if (!jobExists) {
                JobDetail jobDetail = getJobDetail(ticketingAlert);
                ticketingAlertscheduler.addJob(jobDetail, true, true);
            }

            List<TriggerKey> triggerKeysToRemove = ticketingAlert.alertTimesToRemove().stream()
                .map(alertTime -> getTriggerKey(ticketingAlert, alertTime))
                .toList();
            ticketingAlertscheduler.unscheduleJobs(triggerKeysToRemove);

            for (LocalDateTime alertTime : ticketingAlert.alertTimesToAdd()) {
                Trigger trigger = newTrigger()
                    .withIdentity(getTriggerKey(ticketingAlert, alertTime))
                    .startAt(Date.from(alertTime.atZone(ZoneId.systemDefault()).toInstant()))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(5)
                        .withMisfireHandlingInstructionFireNow())
                    .forJob(jobKey)
                    .build();

                ticketingAlertscheduler.scheduleJob(trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private TriggerKey getTriggerKey(
        TicketingAlertServiceResponse ticketingAlert,
        LocalDateTime alertTime
    ) {
        return TriggerKey.triggerKey(
            ticketingAlert.userFcmToken() + " : "
                + ticketingAlert.showId() + " : "
                + alertTime
        );
    }

    private JobDetail getJobDetail(TicketingAlertServiceResponse ticketingAlert) {
        JobKey jobKey = getJobKey(ticketingAlert);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("userFcmToken", ticketingAlert.userFcmToken());
        jobDataMap.put("name", ticketingAlert.name());

        return JobBuilder.newJob(TicketingAlertQuartzJob.class)
            .withIdentity(jobKey)
            .usingJobData(jobDataMap)
            .build();
    }

    private JobKey getJobKey(TicketingAlertServiceResponse ticketingAlert) {
        return new JobKey(
            ticketingAlert.userFcmToken() + " : "
                + ticketingAlert.showId()
        );
    }
}
