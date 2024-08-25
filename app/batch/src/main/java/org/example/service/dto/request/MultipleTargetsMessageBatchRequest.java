package org.example.service.dto.request;

import java.util.List;
import org.example.message.MessageParam;

public record MultipleTargetsMessageBatchRequest(
    List<String> fcmTokens,
    MessageParam message
) {

}
