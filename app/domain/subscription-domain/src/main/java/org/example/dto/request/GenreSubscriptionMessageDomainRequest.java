package org.example.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record GenreSubscriptionMessageDomainRequest(
    String userFcmToken,
    List<GenreMessageDomainRequest> genres
) {

}
