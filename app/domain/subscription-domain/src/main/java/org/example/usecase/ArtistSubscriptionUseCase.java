package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.repository.subscription.artistsubscription.ArtistSubscriptionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistSubscriptionUseCase {

    private final ArtistSubscriptionRepository artistSubscriptionRepository;

}
