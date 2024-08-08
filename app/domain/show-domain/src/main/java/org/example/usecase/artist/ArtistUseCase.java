package org.example.usecase.artist;

import lombok.RequiredArgsConstructor;
import org.example.repository.artist.ArtistRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistUseCase {

    private final ArtistRepository artistRepository;

}

