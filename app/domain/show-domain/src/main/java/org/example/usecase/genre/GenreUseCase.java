package org.example.usecase.genre;

import lombok.RequiredArgsConstructor;
import org.example.repository.genre.GenreRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreUseCase {

    private final GenreRepository genreRepository;

}
