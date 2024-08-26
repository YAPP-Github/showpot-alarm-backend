package org.example.repository.subscription.artistsubscription;

import static org.example.entity.QArtistSubscription.artistSubscription;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.ArtistSubscriptionDomainResponse;
import org.example.entity.ArtistSubscription;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistSubscriptionQuerydslRepositoryImpl implements
    ArtistSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ArtistSubscriptionDomainResponse> findArtistSubscriptionsByArtistIds(List<UUID> artistIds) {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    ArtistSubscriptionDomainResponse.class,
                    artistSubscription.userFcmToken,
                    artistSubscription.artistName
                )
            )
            .from(artistSubscription)
            .where(artistSubscription.artistId.in(artistIds)
                .and(artistSubscription.isDeleted.isFalse()))
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
