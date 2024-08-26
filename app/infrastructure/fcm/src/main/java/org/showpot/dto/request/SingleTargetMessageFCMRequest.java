package org.showpot.dto.request;

import lombok.Builder;
import org.example.message.MessageParam;
import org.example.service.dto.SingleTargetMessageServiceRequest;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;

@Builder
public record SingleTargetMessageFCMRequest(
    String fcmToken,
    MessageParam message
) {

    public static SingleTargetMessageFCMRequest toFCMRequest(
        SingleTargetMessageServiceRequest request
    ) {
        return SingleTargetMessageFCMRequest.builder()
            .fcmToken(request.fcmToken())
            .message(request.message())
            .build();
    }

    public static SingleTargetMessageFCMRequest toFCMRequest(
        SingleTargetMessageBatchRequest request
    ) {
        return SingleTargetMessageFCMRequest.builder()
            .fcmToken(request.fcmToken())
            .message(request.message())
            .build();
    }

}
