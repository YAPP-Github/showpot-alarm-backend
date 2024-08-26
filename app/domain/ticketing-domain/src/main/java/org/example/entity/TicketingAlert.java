package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.vo.TicketingAlertTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ticketing_alert")
public class TicketingAlert extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "schedule_alert_time", nullable = false)
    private LocalDateTime alertTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ticketing_alert_time", nullable = false)
    private TicketingAlertTime ticketingAlertTime;

    @Column(name = "user_fcm_token", nullable = false)
    private String userFcmToken;

    @Column(name = "show_id", nullable = false)
    private UUID showId;

    @Builder
    private TicketingAlert(
        String name,
        LocalDateTime alertTime,
        TicketingAlertTime ticketingAlertTime,
        String userFcmToken,
        UUID showId
    ) {
        this.name = name;
        this.alertTime = alertTime;
        this.ticketingAlertTime = ticketingAlertTime;
        this.userFcmToken = userFcmToken;
        this.showId = showId;
    }
}
