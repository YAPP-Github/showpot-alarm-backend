package org.example.usecase.show;

import lombok.RequiredArgsConstructor;
import org.example.repository.show.ShowTicketingTimeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowUseCase {

    private final ShowTicketingTimeRepository showTicketingTimeRepository;

}
