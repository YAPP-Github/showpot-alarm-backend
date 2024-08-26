package org.example.service.dto;

import org.example.message.MessageParam;

public record SingleTargetMessageServiceRequest(
    String fcmToken,
    MessageParam message
) {

}
