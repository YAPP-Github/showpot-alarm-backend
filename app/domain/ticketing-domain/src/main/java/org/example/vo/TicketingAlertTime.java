package org.example.vo;

public enum TicketingAlertTime {
    BEFORE_24(24),
    BEFORE_6(6),
    BEFORE_1(1);

    private final long time;

    TicketingAlertTime(long time) {
        this.time = time;
    }
}
