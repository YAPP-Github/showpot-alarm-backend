package org.example.service.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.response.TicketingAlertToSchedulerDomainResponse;

@Builder
public record TicketingAlertServiceResponse(
    String name,
    String userFcmToken,
    UUID showId,
    List<LocalDateTime> alertTimesToAdd,
    List<LocalDateTime> alertTimesToRemove
) {

    public static TicketingAlertServiceResponse from(
        TicketingAlertToSchedulerDomainResponse ticketingAlert
    ) {
        return TicketingAlertServiceResponse.builder()
            .name(ticketingAlert.name())
            .userFcmToken(ticketingAlert.userFcmToken())
            .showId(ticketingAlert.showId())
            .alertTimesToAdd(ticketingAlert.alertTimesToAdd())
            .alertTimesToRemove(ticketingAlert.alertTimesToRemove())
            .build();
    }
}
