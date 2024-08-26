package org.example.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import org.example.vo.TicketingAlertTime;

@Builder
public record TicketingTimeMessageDomainRequest(
    LocalDateTime alertAt,
    TicketingAlertTime time
) {

}
