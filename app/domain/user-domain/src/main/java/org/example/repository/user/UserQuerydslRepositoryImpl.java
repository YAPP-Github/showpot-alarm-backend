package org.example.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQuerydslRepositoryImpl implements UserQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

}
