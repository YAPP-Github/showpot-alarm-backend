package org.example.listener.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.service.dto.SubscriptionMessageServiceRequest;

@Builder
public record ShowRelationSubscriptionMessageApiRequest(
    List<UUID> artistIds,
    List<UUID> genreIds
) {

    public SubscriptionMessageServiceRequest toServiceRequest() {
        return SubscriptionMessageServiceRequest.builder()
            .artistIds(artistIds)
            .genreIds(genreIds)
            .build();
    }
}
