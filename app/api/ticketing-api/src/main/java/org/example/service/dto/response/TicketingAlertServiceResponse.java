package org.example.service.dto.response;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.response.TicketingAlertToSchedulerDomainResponse;

@Builder
public record TicketingAlertServiceResponse(
    String name,
    String userFcmToken,
    UUID showId,
    List<TicketingAlertTimeServiceResponse> alertTimesToAdd,
    List<TicketingAlertTimeServiceResponse> alertTimesToRemove
) {

    public static TicketingAlertServiceResponse from(
        TicketingAlertToSchedulerDomainResponse ticketingAlert
    ) {
        return TicketingAlertServiceResponse.builder()
            .name(ticketingAlert.name())
            .userFcmToken(ticketingAlert.userFcmToken())
            .showId(ticketingAlert.showId())
            .alertTimesToAdd(
                ticketingAlert.alertTimesToAdd().stream()
                    .map(TicketingAlertTimeServiceResponse::from)
                    .toList()
            )
            .alertTimesToRemove(
                ticketingAlert.alertTimesToRemove().stream()
                    .map(TicketingAlertTimeServiceResponse::from)
                    .toList()
            )
            .build();
    }
}
