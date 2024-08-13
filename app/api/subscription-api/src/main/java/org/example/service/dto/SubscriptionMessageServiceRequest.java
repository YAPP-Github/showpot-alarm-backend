package org.example.service.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record SubscriptionMessageServiceRequest(
    List<UUID> artistIds,
    List<UUID> genreIds
) {

}
