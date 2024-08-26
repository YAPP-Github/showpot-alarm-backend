package org.example.service.dto.request;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.request.TicketingReservationMessageDomainRequest;

@Builder
public record TicketingReservationMessageServiceRequest(
    String userFcmToken,
    String name,
    UUID showId,
    List<TicketingTimeMessageServiceRequest> addAts,
    List<TicketingTimeMessageServiceRequest> deleteAts
) {

    public TicketingReservationMessageDomainRequest toDomainRequest() {
        return TicketingReservationMessageDomainRequest.builder()
            .userFcmToken(userFcmToken)
            .name(name)
            .showId(showId)
            .addAts(addAts.stream().map(TicketingTimeMessageServiceRequest::toDomainRequest).toList())
            .deleteAts(deleteAts.stream().map(TicketingTimeMessageServiceRequest::toDomainRequest).toList())
            .build();
    }
}
