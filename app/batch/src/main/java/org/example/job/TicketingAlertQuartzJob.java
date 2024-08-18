package org.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


@Component
public class TicketingAlertQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String userFcmToken = context.getMergedJobDataMap().getString("userFcmToken");
        String name = context.getMergedJobDataMap().getString("name");

        //TODO FCM 알림 로직
    }
}
