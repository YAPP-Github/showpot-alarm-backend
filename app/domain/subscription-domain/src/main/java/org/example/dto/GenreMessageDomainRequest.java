package org.example.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record GenreMessageDomainRequest(
    UUID genreId,
    String genreName
) {

}
