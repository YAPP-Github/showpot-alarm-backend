package org.example.service.dto;

import java.util.UUID;
import lombok.Builder;
import org.example.dto.request.ArtistMessageDomainRequest;

@Builder
public record ArtistMessageServiceRequest(
    UUID artistId,
    String artistName
) {
    public ArtistMessageDomainRequest toDomainRequest() {
        return ArtistMessageDomainRequest.builder()
            .artistId(artistId)
            .artistName(artistName)
            .build();
    }
}
