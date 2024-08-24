package org.example.service.dto.request;

import lombok.Builder;
import org.example.message.MessageParam;

@Builder
public record SingleTargetMessageBatchRequest(
    String fcmToken,
    MessageParam message
) {

}
