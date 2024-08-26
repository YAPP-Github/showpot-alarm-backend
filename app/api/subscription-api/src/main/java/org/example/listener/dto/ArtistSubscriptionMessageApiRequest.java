package org.example.listener.dto;

import java.util.List;
import lombok.Builder;
import org.example.service.dto.ArtistSubscriptionMessageServiceRequest;

@Builder
public record ArtistSubscriptionMessageApiRequest(
    String userFcmToken,
    List<ArtistMessageApiRequest> artists
) {

    public ArtistSubscriptionMessageServiceRequest toServiceRequest() {
        return ArtistSubscriptionMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .artists(artists.stream().map(ArtistMessageApiRequest::toServiceRequest).toList())
            .build();
    }
}
