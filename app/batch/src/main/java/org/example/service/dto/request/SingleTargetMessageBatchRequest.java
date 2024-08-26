package org.example.service.dto.request;

import lombok.Builder;
import org.example.message.MessageParam;

@Builder
public record SingleTargetMessageBatchRequest(
    String fcmToken,
    MessageParam message
) {

    public static SingleTargetMessageBatchRequest from(String fcmToken, MessageParam message) {
        return SingleTargetMessageBatchRequest.builder()
            .fcmToken(fcmToken)
            .message(message)
            .build();
    }

}
