package org.example.usecase;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.repository.subscription.genresubscription.GenreSubscriptionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreSubscriptionUseCase {

    private final GenreSubscriptionRepository genreSubscriptionRepository;

    public List<String> findUserFcmTokensByGenreIds(List<UUID> genreIds) {
        return genreSubscriptionRepository.findUserFcmTokensByGenreIds(genreIds);
    }
}
