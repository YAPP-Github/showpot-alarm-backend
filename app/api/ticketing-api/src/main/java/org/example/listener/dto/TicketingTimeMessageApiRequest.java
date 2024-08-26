package org.example.listener.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import org.example.service.dto.request.TicketingTimeMessageServiceRequest;
import org.example.vo.TicketingAlertTimeApiType;

@Builder
public record TicketingTimeMessageApiRequest(
    String alertAt,
    TicketingAlertTimeApiType time
) {

    public TicketingTimeMessageServiceRequest toServiceRequest() {
        return TicketingTimeMessageServiceRequest.builder()
            .alertAt(LocalDateTime.parse(alertAt))
            .time(time)
            .build();
    }

}
