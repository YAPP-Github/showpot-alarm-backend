package org.example.dto.request;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TicketingReservationMessageDomainRequest(
    String userFcmToken,
    String name,
    UUID showId,
    List<TicketingTimeMessageDomainRequest> addAts,
    List<TicketingTimeMessageDomainRequest> deleteAts
) {

}
