package org.example.usecase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.TicketingAlertTargetDomainResponse;
import org.example.dto.request.TicketingReservationMessageDomainRequest;
import org.example.dto.request.TicketingTimeMessageDomainRequest;
import org.example.dto.response.TicketingAlertTimeDomainResponse;
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

    public List<TicketingAlertToSchedulerDomainResponse> findAllTicketingAlerts() {
        Map<TicketingAlertTargetDomainResponse, List<TicketingAlertTimeDomainResponse>> groupedAlerts =
            ticketingAlertRepository.findAllByIsDeletedFalse()
            .stream()
            .collect(Collectors.groupingBy(
                TicketingAlertTargetDomainResponse::from,
                Collectors.mapping(
                    TicketingAlertTimeDomainResponse::from,
                    Collectors.toList()
                )
            ));

        return groupedAlerts.entrySet().stream()
            .map(entry -> TicketingAlertToSchedulerDomainResponse.as(
                    entry.getKey(),
                    entry.getValue()
                )
            )
            .toList();
    }

    @Transactional
    public TicketingAlertToSchedulerDomainResponse reserveTicketingAlert(
        TicketingReservationMessageDomainRequest ticketingReservations
    ) {
        return TicketingAlertToSchedulerDomainResponse.as(
            ticketingReservations,
            addAlerts(ticketingReservations),
            removeAlerts(ticketingReservations)
        );
    }

    private List<TicketingAlertTimeDomainResponse> addAlerts(
        TicketingReservationMessageDomainRequest ticketingReservations
    ) {
        List<TicketingAlert> alertsToAdd = ticketingReservations.addAts().stream()
            .map(addAt -> TicketingAlert.builder()
                .name(ticketingReservations.name())
                .alertTime(addAt.alertAt())
                .ticketingAlertTime(addAt.time())
                .userFcmToken(ticketingReservations.userFcmToken())
                .showId(ticketingReservations.showId())
                .build()
            )
            .toList();
        ticketingAlertRepository.saveAll(alertsToAdd);

        return alertsToAdd.stream().map(TicketingAlertTimeDomainResponse::from).toList();
    }

    private List<TicketingAlertTimeDomainResponse> removeAlerts(
        TicketingReservationMessageDomainRequest ticketingReservations
    ) {
        List<TicketingAlert> existingAlerts = ticketingAlertRepository.findAllByUserFcmTokenAndShowIdAndIsDeletedFalse(
            ticketingReservations.userFcmToken(),
            ticketingReservations.showId()
        );

        List<LocalDateTime> timeToRemove = ticketingReservations.deleteAts().stream()
            .map(TicketingTimeMessageDomainRequest::alertAt)
            .toList();

        List<TicketingAlert> alertsToRemove = existingAlerts.stream()
            .filter(alert -> timeToRemove.contains(alert.getAlertTime()))
            .toList();
        alertsToRemove.forEach(BaseEntity::softDelete);

        return alertsToRemove.stream().map(TicketingAlertTimeDomainResponse::from).toList();
    }
}
