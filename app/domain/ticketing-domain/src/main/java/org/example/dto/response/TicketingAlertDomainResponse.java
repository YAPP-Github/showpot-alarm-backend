package org.example.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.request.TicketingReservationMessageDomainRequest;
import org.example.entity.TicketingAlert;

@Builder
public record TicketingAlertDomainResponse(
    String name,
    String userFcmToken,
    UUID showId,
    List<LocalDateTime> alertTimes
) {


    public static TicketingAlertDomainResponse from(
        TicketingReservationMessageDomainRequest ticketingReservation,
        List<TicketingAlert> ticketingAlerts
    ) {
        return TicketingAlertDomainResponse.builder()
            .name(ticketingReservation.name())
            .userFcmToken(ticketingReservation.userFcmToken())
            .showId(ticketingReservation.showId())
            .alertTimes(extractAlertTimes(ticketingAlerts))
            .build();
    }

    private static List<LocalDateTime> extractAlertTimes(List<TicketingAlert> ticketingAlerts) {
        return ticketingAlerts.isEmpty()
            ? List.of()
            : ticketingAlerts.stream()
                .map(TicketingAlert::getAlertTime)
                .toList();
    }
}
