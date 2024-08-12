package org.example.repository.subscription.genresubscription;

import java.util.List;
import java.util.UUID;

public interface GenreSubscriptionQuerydslRepository {

    List<String> findUserFcmTokensByGenreIds(List<UUID> genreIds);
}
