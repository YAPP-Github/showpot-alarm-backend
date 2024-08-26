package org.example.vo;

public enum TicketingAlertTimeApiType {
    BEFORE_24(24),
    BEFORE_6(6),
    BEFORE_1(1);

    private final int time;

    TicketingAlertTimeApiType(int time) {
        this.time = time;
    }

    public TicketingAlertTime toDomainType() {
        return switch (this) {
            case BEFORE_24 -> TicketingAlertTime.BEFORE_24;
            case BEFORE_6 -> TicketingAlertTime.BEFORE_6;
            case BEFORE_1 -> TicketingAlertTime.BEFORE_1;
        };
    }

    public static TicketingAlertTimeApiType getTicketingAlertTime(TicketingAlertTime alertTime) {
        return switch (alertTime) {
            case BEFORE_24 -> TicketingAlertTimeApiType.BEFORE_24;
            case BEFORE_6 -> TicketingAlertTimeApiType.BEFORE_6;
            case BEFORE_1 -> TicketingAlertTimeApiType.BEFORE_1;
        };
    }

    public String getTime() {
        return String.valueOf(this.time);
    }
}
