package org.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;


@Component
public class TicketingAlertQuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        String userFcmToken = context.getMergedJobDataMap().getString("userFcmToken");
        String name = context.getMergedJobDataMap().getString("name");

        //TODO FCM 알림 로직
        try {
            performJobTask(context);
        } catch (Exception e) {
            handleJobFailure(context);
        }
    }

    // Job 실행 로직
    private void performJobTask(JobExecutionContext context) {
    }

    // 실패 시 재시도 로직
    private void handleJobFailure(JobExecutionContext context) {

    }
}
