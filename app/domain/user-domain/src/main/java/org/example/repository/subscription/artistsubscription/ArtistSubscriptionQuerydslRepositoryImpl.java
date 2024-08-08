package org.example.repository.subscription.artistsubscription;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistSubscriptionQuerydslRepositoryImpl implements
    ArtistSubscriptionQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;


}
