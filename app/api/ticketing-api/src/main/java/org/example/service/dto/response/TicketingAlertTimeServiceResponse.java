package org.example.service.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import org.example.dto.response.TicketingAlertTimeDomainResponse;
import org.example.vo.TicketingAlertTimeApiType;

@Builder
public record TicketingAlertTimeServiceResponse(
    LocalDateTime alertAt,
    TicketingAlertTimeApiType ticketingAlertTime
) {

    public static TicketingAlertTimeServiceResponse from(
        TicketingAlertTimeDomainResponse response
    ) {
        return TicketingAlertTimeServiceResponse.builder()
            .alertAt(response.alertAt())
            .ticketingAlertTime(
                TicketingAlertTimeApiType.getTicketingAlertTime(response.ticketingAlertTime())
            )
            .build();
    }

}
