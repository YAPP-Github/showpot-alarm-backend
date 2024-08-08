package org.example.repository.subscription.genresubscription;

import java.util.UUID;
import org.example.entity.GenreSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreSubscriptionRepository extends JpaRepository<GenreSubscription, UUID>,
    GenreSubscriptionQuerydslRepository {

}
