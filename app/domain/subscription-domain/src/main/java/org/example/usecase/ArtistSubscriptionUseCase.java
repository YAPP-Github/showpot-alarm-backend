package org.example.usecase;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.repository.subscription.artistsubscription.ArtistSubscriptionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistSubscriptionUseCase {

    private final ArtistSubscriptionRepository artistSubscriptionRepository;

    public List<String> findUserFcmTokensByArtistIds(List<UUID> artistIds) {
        return artistSubscriptionRepository.findUserFcmTokensByArtistIds(artistIds);
    }
}
