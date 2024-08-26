package org.example.service.dto;

import java.util.UUID;
import lombok.Builder;
import org.example.dto.request.GenreMessageDomainRequest;

@Builder
public record GenreMessageServiceRequest(
    UUID genreId,
    String genreName
) {

    public GenreMessageDomainRequest toDomainRequest() {
        return GenreMessageDomainRequest.builder()
            .genreId(genreId)
            .genreName(genreName)
            .build();
    }
}
