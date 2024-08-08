package org.example.repository.artist;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArtistQuerydslRepositoryImpl implements ArtistQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
