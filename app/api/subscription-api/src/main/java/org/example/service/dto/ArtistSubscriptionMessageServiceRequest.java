package org.example.service.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.config.ArtistSubscriptionMessageDomainRequest;

@Builder
public record ArtistSubscriptionMessageServiceRequest(
    String userFcmToken,
    List<UUID> artistIds
) {

    public ArtistSubscriptionMessageDomainRequest toDomainRequest() {
        return ArtistSubscriptionMessageDomainRequest.builder()
            .userFcmToken(userFcmToken)
            .artistIds(artistIds)
            .build();
    }
}
