package org.example.dto.response;

public record GenreSubscriptionDomainResponse(
    String userFcmToken,
    String genreName
) {

}
