package org.example.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import org.example.entity.TicketingAlert;
import org.example.vo.TicketingAlertTime;

@Builder
public record TicketingAlertTimeDomainResponse(
    LocalDateTime alertAt,
    TicketingAlertTime ticketingAlertTime
) {

    public static TicketingAlertTimeDomainResponse from(TicketingAlert ticketingAlert) {
        return TicketingAlertTimeDomainResponse.builder()
            .alertAt(ticketingAlert.getAlertTime())
            .ticketingAlertTime(ticketingAlert.getTicketingAlertTime())
            .build();
    }

}
