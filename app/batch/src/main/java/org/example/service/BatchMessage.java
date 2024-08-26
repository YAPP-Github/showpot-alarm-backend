package org.example.service;

import org.example.service.dto.request.MultipleTargetsMessageBatchRequest;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;

public interface BatchMessage {

    void send(SingleTargetMessageBatchRequest request);

    void send(MultipleTargetsMessageBatchRequest request);
}
