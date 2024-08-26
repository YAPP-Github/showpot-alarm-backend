package org.example.listener.dto;

import java.util.List;
import lombok.Builder;
import org.example.service.dto.GenreSubscriptionMessageServiceRequest;

@Builder
public record GenreSubscriptionMessageApiRequest(
    String userFcmToken,
    List<GenreMessageApiRequest> genres
) {

    public GenreSubscriptionMessageServiceRequest toServiceRequest() {
        return GenreSubscriptionMessageServiceRequest.builder()
            .userFcmToken(userFcmToken)
            .genres(genres.stream().map(GenreMessageApiRequest::toServiceRequest).toList())
            .build();
    }
}
