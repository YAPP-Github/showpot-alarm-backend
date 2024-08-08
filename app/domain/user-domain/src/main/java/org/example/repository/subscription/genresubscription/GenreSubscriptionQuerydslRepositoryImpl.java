package org.example.repository.subscription.genresubscription;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenreSubscriptionQuerydslRepositoryImpl implements
    GenreSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
