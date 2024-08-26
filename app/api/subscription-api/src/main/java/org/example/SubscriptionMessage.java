package org.example;

import org.example.service.dto.MultipleTargetMessageServiceRequest;
import org.example.service.dto.SingleTargetMessageServiceRequest;

public interface SubscriptionMessage {

    void send(SingleTargetMessageServiceRequest request);

    void send(MultipleTargetMessageServiceRequest request);

}
