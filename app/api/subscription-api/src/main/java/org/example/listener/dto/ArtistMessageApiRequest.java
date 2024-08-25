package org.example.listener.dto;

import java.util.UUID;
import org.example.service.dto.ArtistMessageServiceRequest;

public record ArtistMessageApiRequest(
    UUID artistId,
    String artistName
) {

    public ArtistMessageServiceRequest toServiceRequest() {
        return ArtistMessageServiceRequest.builder()
            .artistId(artistId)
            .artistName(artistName)
            .build();
    }
}
