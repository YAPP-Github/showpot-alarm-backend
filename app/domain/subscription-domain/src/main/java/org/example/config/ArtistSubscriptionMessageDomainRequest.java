package org.example.config;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ArtistSubscriptionMessageDomainRequest(
    String userFcmToken,
    List<UUID> artistIds
) {

}
