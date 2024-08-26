package org.example.dto.request;

import java.util.UUID;
import lombok.Builder;

@Builder
public record GenreMessageDomainRequest(
    UUID genreId,
    String genreName
) {

}
