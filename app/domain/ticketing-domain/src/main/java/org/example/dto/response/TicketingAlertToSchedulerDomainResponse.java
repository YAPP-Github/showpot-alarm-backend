package org.example.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.request.TicketingReservationMessageDomainRequest;

@Builder
public record TicketingAlertToSchedulerDomainResponse(
    String name,
    String userFcmToken,
    UUID showId,
    List<LocalDateTime> alertTimesToAdd,
    List<LocalDateTime> alertTimesToRemove
) {

    public static TicketingAlertToSchedulerDomainResponse as(
        TicketingReservationMessageDomainRequest request,
        List<LocalDateTime> alertTimesToAdd,
        List<LocalDateTime> alertTimesToRemove
    ) {
        return TicketingAlertToSchedulerDomainResponse.builder()
            .name(request.name())
            .userFcmToken(request.userFcmToken())
            .showId(request.showId())
            .alertTimesToAdd(alertTimesToAdd)
            .alertTimesToRemove(alertTimesToRemove)
            .build();
    }
}
