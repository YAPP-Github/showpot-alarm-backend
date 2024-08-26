package org.example.service.dto.request;

import java.time.LocalDateTime;
import lombok.Builder;
import org.example.dto.request.TicketingTimeMessageDomainRequest;
import org.example.vo.TicketingAlertTimeApiType;

@Builder
public record TicketingTimeMessageServiceRequest(
    LocalDateTime alertAt,
    TicketingAlertTimeApiType time
) {

    public TicketingTimeMessageDomainRequest toDomainRequest() {
        return TicketingTimeMessageDomainRequest.builder()
            .alertAt(alertAt)
            .time(time.toDomainType())
            .build();
    }

}
