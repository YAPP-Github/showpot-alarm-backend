package org.example.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record GenreSubscriptionMessageDomainRequest(
    String userFcmToken,
    List<GenreMessageDomainRequest> genres
) {

}
