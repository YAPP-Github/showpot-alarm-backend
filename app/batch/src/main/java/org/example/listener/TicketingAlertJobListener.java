package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

@Slf4j
public class TicketingAlertJobListener implements JobListener {

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("jobKey: {}인 job이 수행 되기 전입니다.",  context.getJobDetail().getKey());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("jobKey: {}인 job이 중단 되었습니다.",  context.getJobDetail().getKey());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("jobKey: {}인 job이 완료 되었습니다.",  context.getJobDetail().getKey());
    }
}
