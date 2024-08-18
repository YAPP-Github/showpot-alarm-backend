package org.example.listener.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.example.service.dto.request.TicketingReservationMessageServiceRequest;

public record TicketingReservationMessageApiRequest(
    String userFcmToken,
    String name,
    UUID showId,
    List<String> reserveAts
) {

    public TicketingReservationMessageServiceRequest toServiceRequest() {
        return TicketingReservationMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .name(name)
            .showId(showId)
            .reserveAts(reserveAts.stream()
                .map(LocalDateTime::parse)
                .toList()
            )
            .build();
    }
}
