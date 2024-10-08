package org.example.listener.dto;

import java.util.List;
import java.util.UUID;
import org.example.service.dto.request.TicketingReservationMessageServiceRequest;

public record TicketingReservationMessageApiRequest(
    String userFcmToken,
    String name,
    UUID showId,
    List<TicketingTimeMessageApiRequest> addAts,
    List<TicketingTimeMessageApiRequest> deleteAts
) {

    public TicketingReservationMessageServiceRequest toServiceRequest() {
        return TicketingReservationMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .name(name)
            .showId(showId)
            .addAts(addAts.stream().map(TicketingTimeMessageApiRequest::toServiceRequest).toList())
            .deleteAts(deleteAts.stream().map(TicketingTimeMessageApiRequest::toServiceRequest).toList())
            .build();
    }
}
