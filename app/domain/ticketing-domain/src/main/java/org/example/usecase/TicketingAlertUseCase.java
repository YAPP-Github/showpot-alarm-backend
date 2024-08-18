package org.example.usecase;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.TicketingReservationMessageDomainRequest;
import org.example.dto.response.TicketingAlertDomainResponse;
import org.example.dto.response.TicketingAlertToSchedulerDomainResponse;
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
    public TicketingAlertToSchedulerDomainResponse reserveTicketingAlert(
        TicketingReservationMessageDomainRequest ticketingReservations
    ) {
        List<TicketingAlert> existingAlerts = ticketingAlertRepository.findAllByUserFcmTokenAndShowIdAndIsDeletedFalse(
            ticketingReservations.userFcmToken(),
            ticketingReservations.showId()
        );

        List<LocalDateTime> existingAlertTimes = existingAlerts.stream()
            .map(TicketingAlert::getAlertTime)
            .toList();

        TicketingAlertDomainResponse ticketingAlertToAdd = addAlerts(ticketingReservations, existingAlertTimes);
        TicketingAlertDomainResponse ticketingAlertToRemove = removeAlerts(ticketingReservations, existingAlerts);

        return TicketingAlertToSchedulerDomainResponse.from(ticketingAlertToAdd, ticketingAlertToRemove);
    }

    private TicketingAlertDomainResponse addAlerts(
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

        return TicketingAlertDomainResponse.from(ticketingReservation, alertsToAdd);
    }

    private TicketingAlertDomainResponse removeAlerts(
        TicketingReservationMessageDomainRequest ticketingReservation,
        List<TicketingAlert> existingAlerts
    ) {
        List<TicketingAlert> alertsToRemove = existingAlerts.stream()
            .filter(alert -> !ticketingReservation.reserveAts().contains(alert.getAlertTime()))
            .toList();
        alertsToRemove.forEach(BaseEntity::softDelete);

        return TicketingAlertDomainResponse.from(ticketingReservation, alertsToRemove);
    }
}
