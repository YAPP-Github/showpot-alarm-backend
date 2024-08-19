package org.example.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.TicketingAlertTargetDomainResponse;
import org.example.dto.request.TicketingReservationMessageDomainRequest;
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

        List<LocalDateTime> alertsTimeToAdd = addAlerts(ticketingReservations);
        List<LocalDateTime> alertsTimeToRemove = removeAlerts(ticketingReservations,
            existingAlerts);

        return TicketingAlertToSchedulerDomainResponse.as(
            ticketingReservations,
            alertsTimeToAdd,
            alertsTimeToRemove
        );
    }

    private List<LocalDateTime> addAlerts(
        TicketingReservationMessageDomainRequest ticketingReservation
    ) {
        List<TicketingAlert> alertsToAdd = ticketingReservation.addAts().stream()
            .map(addAt -> TicketingAlert.builder()
                .name(ticketingReservation.name())
                .alertTime(addAt)
                .userFcmToken(ticketingReservation.userFcmToken())
                .showId(ticketingReservation.showId())
                .build()
            )
            .toList();
        ticketingAlertRepository.saveAll(alertsToAdd);

        return alertsToAdd.stream().map(TicketingAlert::getAlertTime).toList();
    }

    private List<LocalDateTime> removeAlerts(
        TicketingReservationMessageDomainRequest ticketingReservation,
        List<TicketingAlert> existingAlerts
    ) {
        List<TicketingAlert> alertsToRemove = existingAlerts.stream()
            .filter(alert -> ticketingReservation.deleteAts().contains(alert.getAlertTime()))
            .toList();
        alertsToRemove.forEach(BaseEntity::softDelete);

        return alertsToRemove.stream().map(TicketingAlert::getAlertTime).toList();
    }

    public List<TicketingAlertToSchedulerDomainResponse> findAllTicketingAlerts() {
        Map<TicketingAlertTargetDomainResponse, List<LocalDateTime>> groupedAlerts = ticketingAlertRepository
            .findAllByIsDeletedFalse()
            .stream()
            .collect(
                Collectors.groupingBy(
                    TicketingAlertTargetDomainResponse::from,
                    Collectors.mapping(TicketingAlert::getAlertTime, Collectors.toList())
                )
            );

        return groupedAlerts.entrySet().stream()
            .map(entry -> TicketingAlertToSchedulerDomainResponse.as(
                    entry.getKey(),
                    entry.getValue()
                )
            )
            .toList();
    }
}
