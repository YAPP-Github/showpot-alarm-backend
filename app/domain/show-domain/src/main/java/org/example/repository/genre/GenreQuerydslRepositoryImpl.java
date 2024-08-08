package org.example.repository.genre;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenreQuerydslRepositoryImpl implements GenreQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
