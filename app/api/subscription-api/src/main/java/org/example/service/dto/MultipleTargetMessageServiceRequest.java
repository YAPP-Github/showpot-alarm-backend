package org.example.service.dto;

import java.util.List;
import lombok.Builder;
import org.example.message.MessageParam;

@Builder
public record MultipleTargetMessageServiceRequest(
    List<String> fcmTokens,
    MessageParam message
) {
    public static MultipleTargetMessageServiceRequest of(
        List<String> fcmTokens,
        MessageParam message
    ) {
        return MultipleTargetMessageServiceRequest.builder()
            .fcmTokens(fcmTokens)
            .message(message)
            .build();
    }
}
