package org.showpot.service;

import lombok.RequiredArgsConstructor;
import org.example.service.MessageService;
import org.example.service.dto.request.MultipleTargetsMessageBatchRequest;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;
import org.showpot.client.FCMClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMMessageService implements MessageService {

    private final FCMClient fcmClient;

    @Override
    public void send(SingleTargetMessageBatchRequest request) {

    }

    @Override
    public void send(MultipleTargetsMessageBatchRequest request) {

    }
}
