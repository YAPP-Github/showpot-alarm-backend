package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.service.dto.request.TicketingReservationMessageServiceRequest;
import org.example.usecase.TicketingAlertUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketingAlertService {

    private final TicketingAlertUseCase ticketingAlertUseCase;

    public void reserveTicketingAlert(TicketingReservationMessageServiceRequest request) {
        ticketingAlertUseCase.reserveTicketingAlert(request.toDomainRequest());

        //Todo Batch 작업 : 해당 공연에 대한 리스트를 뽑아서, 삭제 되어 있는것은 배치에서 삭제하고,추가된것은 배치에 넣음.
        // -> 배치 이후 FCM
    }
}
