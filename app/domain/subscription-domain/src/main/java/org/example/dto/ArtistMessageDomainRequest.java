package org.example.dto;

import java.util.UUID;
import lombok.Builder;

@Builder
public record ArtistMessageDomainRequest(
    UUID artistId,
    String artistName
) {

}
