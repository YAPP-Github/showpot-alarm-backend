package org.example.listener.dto;

import java.util.UUID;
import org.example.service.dto.GenreMessageServiceRequest;

public record GenreMessageApiRequest(
    UUID genreId,
    String genreName
) {

    public GenreMessageServiceRequest toServiceRequest() {
        return GenreMessageServiceRequest.builder()
            .genreId(genreId)
            .genreName(genreName)
            .build();
    }
}
