package org.example.dto.request;

import java.util.UUID;
import lombok.Builder;
import org.example.entity.TicketingAlert;

@Builder
public record TicketingAlertTargetDomainResponse(
    String userFcmToken,
    UUID showId,
    String name
) {
    public static TicketingAlertTargetDomainResponse from(TicketingAlert ticketingAlert) {
        return TicketingAlertTargetDomainResponse.builder()
            .userFcmToken(ticketingAlert.getUserFcmToken())
            .showId(ticketingAlert.getShowId())
            .name(ticketingAlert.getName())
            .build();
    }

}
