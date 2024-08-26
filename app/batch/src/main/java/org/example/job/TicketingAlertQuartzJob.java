package org.example.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.message.PushMessageTemplate;
import org.example.service.BatchMessage;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TicketingAlertQuartzJob implements Job {

    private final BatchMessage batchMessage;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            performJobTask(context);
        } catch (JobExecutionException e) {
            handleJobFailure(context);
        }
    }

    // Job 실행 로직
    private void performJobTask(JobExecutionContext context) throws JobExecutionException {
        String userFcmToken = context.getMergedJobDataMap().getString("userFcmToken");
        String name = context.getMergedJobDataMap().getString("name");
        String time = context.getTrigger().getKey().getGroup();

        batchMessage.send(
            SingleTargetMessageBatchRequest.from(
                userFcmToken,
                PushMessageTemplate.getTicketingAlertMessageBeforeHours(name, time)
            )
        );
    }

    // 실패 시 재시도 로직
    private void handleJobFailure(JobExecutionContext context) {
        int retryCount = context.getMergedJobDataMap().getIntValue("retryCount");
        log.warn("Job failed for JobDetail key: {}, Trigger key: {}. Retry count: {}",
            context.getJobDetail().getKey(),
            context.getTrigger().getKey(),
            retryCount + 1
        );

        if (retryCount < 3) {
            context.getMergedJobDataMap().put("retryCount", retryCount + 1);
            try {
                performJobTask(context);
            } catch (JobExecutionException e) {
                handleJobFailure(context);
            }
        } else {
            log.error("Max retry limit reached for JobDetail key: {}, Trigger key: {}",
                context.getJobDetail().getKey(),
                context.getTrigger().getKey()
            );
        }
    }
}
