package org.example.repository.subscription.artistsubscription;

import java.util.List;
import java.util.UUID;
import org.example.entity.ArtistSubscription;

public interface ArtistSubscriptionQuerydslRepository {

    List<String> findUserFcmTokensByArtistIds(List<UUID> artistIds);

    List<ArtistSubscription> findSubscriptionList(String userFcmToken);
}
