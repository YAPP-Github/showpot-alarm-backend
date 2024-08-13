package org.example.listener.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.service.dto.GenreSubscriptionMessageServiceRequest;

@Builder
public record GenreSubscriptionMessageApiRequest(
    String userFcmToken,
    List<UUID> genreIds
) {

    public GenreSubscriptionMessageServiceRequest toServiceRequest() {
        return GenreSubscriptionMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .genreIds(genreIds)
            .build();
    }
}
