package org.showpot.dto.request;

import java.util.List;
import lombok.Builder;
import org.example.message.MessageParam;
import org.example.service.dto.MultipleTargetMessageServiceRequest;
import org.example.service.dto.request.MultipleTargetsMessageBatchRequest;

@Builder
public record MultipleTargetMessageFCMRequest(
    List<String> fcmTokens,
    MessageParam message
) {

    public static MultipleTargetMessageFCMRequest toFCMRequest(
        MultipleTargetMessageServiceRequest request
    ) {
        return MultipleTargetMessageFCMRequest.builder()
            .fcmTokens(request.fcmTokens())
            .message(request.message())
            .build();
    }

    public static MultipleTargetMessageFCMRequest toFCMRequest(
        MultipleTargetsMessageBatchRequest request
    ) {
        return MultipleTargetMessageFCMRequest.builder()
            .fcmTokens(request.fcmTokens())
            .message(request.message())
            .build();
    }

}
