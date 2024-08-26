package org.example.repository.subscription.genresubscription;

import java.util.List;
import java.util.UUID;
import org.example.dto.response.GenreSubscriptionDomainResponse;
import org.example.entity.GenreSubscription;

public interface GenreSubscriptionQuerydslRepository {

    List<GenreSubscriptionDomainResponse> findGenreSubscriptionsByGenreIds(List<UUID> genreIds);

    List<GenreSubscription> findSubscriptionList(String userFcmToken);
}
