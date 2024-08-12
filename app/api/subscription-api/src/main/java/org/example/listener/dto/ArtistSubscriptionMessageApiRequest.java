package org.example.listener.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.service.dto.ArtistSubscriptionMessageServiceRequest;

@Builder
public record ArtistSubscriptionMessageApiRequest(
    String userFcmToken,
    List<UUID> artistIds
) {

    public ArtistSubscriptionMessageServiceRequest toServiceRequest() {
        return ArtistSubscriptionMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .artistIds(artistIds)
            .build();
    }
}
