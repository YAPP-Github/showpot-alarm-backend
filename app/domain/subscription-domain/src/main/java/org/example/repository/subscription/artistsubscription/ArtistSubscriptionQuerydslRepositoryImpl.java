package org.example.repository.subscription.artistsubscription;

import static org.example.entity.QArtistSubscription.artistSubscription;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.entity.ArtistSubscription;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistSubscriptionQuerydslRepositoryImpl implements
    ArtistSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> findUserFcmTokensByArtistIds(List<UUID> artistIds) {
        return jpaQueryFactory
            .select(artistSubscription.userFcmToken)
            .from(artistSubscription)
            .where(artistSubscription.artistId.in(artistIds))
            .fetch();
    }

    @Override
    public List<ArtistSubscription> findSubscriptionList(String userFcmToken) {
        return jpaQueryFactory
            .selectFrom(artistSubscription)
            .where(artistSubscription.userFcmToken.eq(userFcmToken)
                .and(artistSubscription.isDeleted.isFalse())
            ).fetch();
    }
}
