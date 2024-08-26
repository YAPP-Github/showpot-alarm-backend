package org.showpot.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.SubscriptionMessage;
import org.example.service.BatchMessage;
import org.example.service.dto.MultipleTargetMessageServiceRequest;
import org.example.service.dto.SingleTargetMessageServiceRequest;
import org.example.service.dto.request.MultipleTargetsMessageBatchRequest;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;
import org.showpot.client.FCMClient;
import org.showpot.dto.param.FCMMessageParam;
import org.showpot.dto.request.MultipleTargetMessageFCMRequest;
import org.showpot.dto.request.SingleTargetMessageFCMRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMMessageService implements BatchMessage, SubscriptionMessage {

    private final FCMClient fcmClient;

    @Override
    public void send(SingleTargetMessageBatchRequest request) {
        SingleTargetMessageFCMRequest fcmRequest = SingleTargetMessageFCMRequest.toFCMRequest(request);
        fcmClient.sendNotification(
            fcmRequest.fcmToken(),
            FCMMessageParam.from(fcmRequest.message()).toNotification()
        );
    }

    @Override
    public void send(MultipleTargetsMessageBatchRequest request) {
        List<MultipleTargetMessageFCMRequest> fcmRequests = splitRequest(
            MultipleTargetMessageFCMRequest.toFCMRequest(request)
        );

        fcmRequests.forEach(splitRequest ->
            fcmClient.sendNotification(
                splitRequest.fcmTokens(),
                FCMMessageParam.from(request.message()).toNotification()
            )
        );
    }

    @Override
    public void send(SingleTargetMessageServiceRequest request) {
        SingleTargetMessageFCMRequest fcmRequest = SingleTargetMessageFCMRequest.toFCMRequest(request);
        fcmClient.sendNotification(
            fcmRequest.fcmToken(),
            FCMMessageParam.from(fcmRequest.message()).toNotification()
        );
    }

    @Override
    public void send(MultipleTargetMessageServiceRequest request) {
        List<MultipleTargetMessageFCMRequest> fcmRequests = splitRequest(
            MultipleTargetMessageFCMRequest.toFCMRequest(request)
        );

        fcmRequests.forEach(splitRequest ->
            fcmClient.sendNotification(
                splitRequest.fcmTokens(),
                FCMMessageParam.from(request.message()).toNotification()
            )
        );
    }

    private List<MultipleTargetMessageFCMRequest> splitRequest(
        MultipleTargetMessageFCMRequest request
    ) {
        List<String> fcmTokens = request.fcmTokens();
        List<MultipleTargetMessageFCMRequest> splitRequests = new ArrayList<>();

        int size = fcmTokens.size();
        int startIndex = 0;

        while (startIndex < size) {
            int endIndex = Math.min(startIndex + 500, size);

            var splitRequest = new MultipleTargetMessageFCMRequest(
                fcmTokens.subList(startIndex, endIndex),
                request.message()
            );
            splitRequests.add(splitRequest);

            startIndex = endIndex;
        }

        return splitRequests;
    }
}
