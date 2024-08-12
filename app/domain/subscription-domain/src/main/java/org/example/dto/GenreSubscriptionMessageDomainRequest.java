package org.example.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record GenreSubscriptionMessageDomainRequest(
    String userFcmToken,
    List<UUID> genreIds
) {

}
