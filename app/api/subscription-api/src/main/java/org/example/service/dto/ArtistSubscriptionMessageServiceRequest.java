package org.example.service.dto;

import java.util.List;
import lombok.Builder;
import org.example.dto.ArtistSubscriptionMessageDomainRequest;

@Builder
public record ArtistSubscriptionMessageServiceRequest(
    String userFcmToken,
    List<ArtistMessageServiceRequest> artists
) {

    public ArtistSubscriptionMessageDomainRequest toDomainRequest() {
        return ArtistSubscriptionMessageDomainRequest.builder()
            .userFcmToken(userFcmToken)
            .artists(artists.stream().map(ArtistMessageServiceRequest::toDomainRequest).toList())
            .build();
    }
}
