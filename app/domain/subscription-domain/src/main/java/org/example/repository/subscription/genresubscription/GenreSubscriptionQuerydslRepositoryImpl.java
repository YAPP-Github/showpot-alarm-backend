package org.example.repository.subscription.genresubscription;

import static org.example.entity.QGenreSubscription.genreSubscription;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.GenreSubscriptionDomainResponse;
import org.example.entity.GenreSubscription;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenreSubscriptionQuerydslRepositoryImpl implements
    GenreSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GenreSubscriptionDomainResponse> findGenreSubscriptionsByGenreIds(List<UUID> genreIds) {
        return jpaQueryFactory
            .select(
                Projections.constructor(
                    GenreSubscriptionDomainResponse.class,
                    genreSubscription.userFcmToken,
                    genreSubscription.genreName
                )
            )
            .from(genreSubscription)
            .where(genreSubscription.genreId.in(genreIds)
                .and(genreSubscription.isDeleted.isFalse()))
            .fetch();
    }

    @Override
    public List<GenreSubscription> findSubscriptionList(String userFcmToken) {
        return jpaQueryFactory
            .selectFrom(genreSubscription)
            .where(genreSubscription.userFcmToken.eq(userFcmToken)
                .and(genreSubscription.isDeleted.isFalse())
            ).fetch();
    }
}
