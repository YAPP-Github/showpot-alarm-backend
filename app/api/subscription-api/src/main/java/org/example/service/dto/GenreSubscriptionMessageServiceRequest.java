package org.example.service.dto;

import java.util.List;
import lombok.Builder;
import org.example.dto.GenreSubscriptionMessageDomainRequest;

@Builder
public record GenreSubscriptionMessageServiceRequest(
    String userFcmToken,
    List<GenreMessageServiceRequest> genres
) {

    public GenreSubscriptionMessageDomainRequest toDomainRequest() {
        return GenreSubscriptionMessageDomainRequest.builder()
            .userFcmToken(userFcmToken)
            .genres(genres.stream().map(GenreMessageServiceRequest::toDomainRequest).toList())
            .build();
    }
}
