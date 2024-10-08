package org.example.repository.subscription.artistsubscription;

import java.util.List;
import java.util.UUID;
import org.example.entity.ArtistSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistSubscriptionRepository
    extends JpaRepository<ArtistSubscription, UUID>, ArtistSubscriptionQuerydslRepository {

    List<ArtistSubscription> findAllByUserFcmToken(String userFcmToken);
}
