package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

@Slf4j
public class TicketingAlertTriggerListener implements TriggerListener {

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("triggerKey:{}인 트리거가 실행됩니다.", trigger.getKey());
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.warn("triggerKey:{}인 트리거가 예정된 시간에 실행되지 못했습니다.", trigger.getKey());
    }

    @Override
    public void triggerComplete(Trigger trigger,
        JobExecutionContext context,
        CompletedExecutionInstruction triggerInstructionCode
    ) {
        log.info("triggerKey:{}인 트리거가 완료되었습니다.", trigger.getKey());
    }
}
