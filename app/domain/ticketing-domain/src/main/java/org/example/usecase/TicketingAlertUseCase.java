package org.example.usecase;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.TicketingReservationMessageDomainRequest;
import org.example.entity.BaseEntity;
import org.example.entity.TicketingAlert;
import org.example.repository.ticketingalert.TicketingAlertRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TicketingAlertUseCase {

    private final TicketingAlertRepository ticketingAlertRepository;

    @Transactional
    public void reserveTicketingAlert(
        TicketingReservationMessageDomainRequest ticketingReservations
    ) {
        List<TicketingAlert> existingAlerts = ticketingAlertRepository.findAllByUserFcmTokenAndShowIdAndIsDeletedFalse(
            ticketingReservations.userFcmToken(),
            ticketingReservations.showId()
        );

        List<LocalDateTime> existingAlertTimes = existingAlerts.stream()
            .map(TicketingAlert::getAlertTime)
            .toList();

        addAlerts(ticketingReservations, existingAlertTimes);
        removeAlerts(ticketingReservations, existingAlerts);
    }

    private void addAlerts(
        TicketingReservationMessageDomainRequest ticketingReservation,
        List<LocalDateTime> existingAlertTimes
    ) {
        List<TicketingAlert> alertsToAdd = ticketingReservation.reserveAts().stream()
            .filter(reserveAt -> !existingAlertTimes.contains(reserveAt))
            .map(reserveAt -> TicketingAlert.builder()
                .name(ticketingReservation.name())
                .alertTime(reserveAt)
                .userFcmToken(ticketingReservation.userFcmToken())
                .showId(ticketingReservation.showId())
                .build()
            )
            .toList();
        ticketingAlertRepository.saveAll(alertsToAdd);
    }

    private void removeAlerts(
        TicketingReservationMessageDomainRequest ticketingReservations,
        List<TicketingAlert> existingAlerts
    ) {
        List<TicketingAlert> alertsToRemove = existingAlerts.stream()
            .filter(alert -> !ticketingReservations.reserveAts().contains(alert.getAlertTime()))
            .toList();
        alertsToRemove.forEach(BaseEntity::softDelete);
    }
}
