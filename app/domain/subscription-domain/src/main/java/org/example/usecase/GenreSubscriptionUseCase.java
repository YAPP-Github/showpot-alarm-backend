package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.repository.subscription.genresubscription.GenreSubscriptionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreSubscriptionUseCase {

    private final GenreSubscriptionRepository genreSubscriptionRepository;

}
