package org.example.repository.subscription.artistsubscription;

import java.util.List;
import java.util.UUID;

public interface ArtistSubscriptionQuerydslRepository {

    List<String> findUserFcmTokensByArtistIds(List<UUID> artistIds);
}
