package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.service.dto.ArtistSubscriptionMessageServiceRequest;
import org.example.service.dto.SubscriptionMessageServiceRequest;
import org.example.usecase.ArtistSubscriptionUseCase;
import org.example.usecase.GenreSubscriptionUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionAlarmService {

    private final ArtistSubscriptionUseCase artistSubscriptionUseCase;
    private final GenreSubscriptionUseCase genreSubscriptionUseCase;


    public void showRelationSubscription(SubscriptionMessageServiceRequest request) {
        var artistSubscribedUserFcmTokens = artistSubscriptionUseCase.findUserFcmTokensByArtistIds(request.artistIds());
        System.out.println(artistSubscribedUserFcmTokens);
        //Todo 아티스트 구독한 user들에게 FCM 알림 요청

        var genreSubscribedUserFcmTokens = genreSubscriptionUseCase.findUserFcmTokensByGenreIds(request.genreIds());
        System.out.println(genreSubscribedUserFcmTokens);
        //Todo 장르 구독한 user들에게 FCM 알림 요청
    }

    public void artistSubscribe(ArtistSubscriptionMessageServiceRequest request) {
        artistSubscriptionUseCase.artistSubscribe(request.toDomainRequest());
    }

    public void artistUnsubscribe(ArtistSubscriptionMessageServiceRequest request) {
        artistSubscriptionUseCase.artistUnsubscribe(request.toDomainRequest());
    }
}
