package org.example.service.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.example.dto.GenreSubscriptionMessageDomainRequest;

@Builder
public record GenreSubscriptionMessageServiceRequest(
    String userFcmToken,
    List<UUID> genreIds
) {

    public GenreSubscriptionMessageDomainRequest toDomainRequest() {
        return GenreSubscriptionMessageDomainRequest.builder()
            .userFcmToken(userFcmToken)
            .genreIds(genreIds)
            .build();
    }
}
