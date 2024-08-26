package org.example.dto.response;

public record ArtistSubscriptionDomainResponse(
    String userFcmToken,
    String artistName
) {

}
