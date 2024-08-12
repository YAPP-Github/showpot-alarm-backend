package org.example.repository.subscription.genresubscription;

import static org.example.entity.QGenreSubscription.genreSubscription;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenreSubscriptionQuerydslRepositoryImpl implements
    GenreSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> findUserFcmTokensByGenreIds(List<UUID> genreIds) {
        return jpaQueryFactory
            .select(genreSubscription.userFcmToken)
            .from(genreSubscription)
            .where(genreSubscription.genreId.in(genreIds))
            .fetch();
    }
}
