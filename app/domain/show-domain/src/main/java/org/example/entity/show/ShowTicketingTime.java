package org.example.entity.show;

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
import org.example.entity.BaseEntity;
import org.example.vo.TicketingType;

@Entity
@Table(name = "show_ticketing_time")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShowTicketingTime extends BaseEntity {

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketingType ticketingType;

    @Column(name = "ticketing_at", nullable = false)
    private LocalDateTime ticketingAt;

    @Column(name = "show_id", nullable = false)
    private UUID showId;

    @Builder
    public ShowTicketingTime(TicketingType ticketingType, LocalDateTime ticketingAt, UUID showId) {
        this.ticketingType = ticketingType;
        this.ticketingAt = ticketingAt;
        this.showId = showId;
    }
}
