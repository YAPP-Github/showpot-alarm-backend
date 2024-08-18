package org.example.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TicketingAlertToSchedulerDomainResponse(
    String name,
    String userFcmToken,
    UUID showId,
    List<LocalDateTime> alertTimesToAdd,
    List<LocalDateTime> alertTimesToRemove
) {

    public static TicketingAlertToSchedulerDomainResponse from(
        TicketingAlertDomainResponse ticketingAlertToAdd,
        TicketingAlertDomainResponse ticketingAlertToRemove
    ) {
        String name = ticketingAlertToAdd.name();
        String userFcmToken = ticketingAlertToAdd.userFcmToken();
        UUID showId = ticketingAlertToAdd.showId();

        return TicketingAlertToSchedulerDomainResponse.builder()
            .name(name)
            .userFcmToken(userFcmToken)
            .showId(showId)
            .alertTimesToAdd(ticketingAlertToAdd.alertTimes())
            .alertTimesToRemove(ticketingAlertToRemove.alertTimes())
            .build();
    }
}
