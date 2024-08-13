package org.example.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.ArtistSubscriptionMessageDomainRequest;
import org.example.entity.ArtistSubscription;
import org.example.repository.subscription.artistsubscription.ArtistSubscriptionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ArtistSubscriptionUseCase {

    private final ArtistSubscriptionRepository artistSubscriptionRepository;

    public List<String> findUserFcmTokensByArtistIds(List<UUID> artistIds) {
        return artistSubscriptionRepository.findUserFcmTokensByArtistIds(artistIds);
    }

    @Transactional
    public void artistSubscribe(ArtistSubscriptionMessageDomainRequest request) {
        var newSubscriptions = new ArrayList<ArtistSubscription>();
        var allSubscriptionByArtistId = artistSubscriptionRepository.findAllByUserFcmToken(request.userFcmToken())
            .stream()
            .collect(Collectors.toMap(ArtistSubscription::getArtistId, it -> it));

        for (UUID artistId : request.artistIds()) {
            if (allSubscriptionByArtistId.containsKey(artistId)) {
                var existSubscription = allSubscriptionByArtistId.get(artistId);
                existSubscription.subscribe();
                continue;
            }

            newSubscriptions.add(ArtistSubscription.builder()
                .userFcmToken(request.userFcmToken())
                .artistId(artistId)
                .build()
            );
        }

        artistSubscriptionRepository.saveAll(newSubscriptions);
    }

    @Transactional
    public void artistUnsubscribe(ArtistSubscriptionMessageDomainRequest request) {
        var subscriptions = artistSubscriptionRepository.findSubscriptionList(request.userFcmToken());
        var filteredSubscription = subscriptions.stream()
            .filter(it -> request.artistIds().contains(it.getArtistId()))
            .toList();

        filteredSubscription.forEach(ArtistSubscription::unsubscribe);
    }
}
