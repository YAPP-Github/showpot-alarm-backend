package org.showpot.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.service.MessageService;
import org.example.service.dto.request.MultipleTargetsMessageBatchRequest;
import org.example.service.dto.request.SingleTargetMessageBatchRequest;
import org.showpot.client.FCMClient;
import org.showpot.dto.param.FCMMessageParam;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FCMMessageService implements MessageService {

    private final FCMClient fcmClient;

    @Override
    public void send(SingleTargetMessageBatchRequest request) {
        fcmClient.sendNotification(
            request.fcmToken(),
            FCMMessageParam.from(request.message()).toNotification()
        );
    }

    @Override
    public void send(MultipleTargetsMessageBatchRequest request) {
        List<MultipleTargetsMessageBatchRequest> requests = splitRequest(request);

        requests.forEach(splitRequest ->
            fcmClient.sendNotification(
                splitRequest.fcmTokens(),
                FCMMessageParam.from(request.message()).toNotification()
            )
        );
    }

    private List<MultipleTargetsMessageBatchRequest> splitRequest(MultipleTargetsMessageBatchRequest request) {
        List<String> fcmTokens = request.fcmTokens();
        List<MultipleTargetsMessageBatchRequest> splitRequests = new ArrayList<>();

        int size = fcmTokens.size();
        int startIndex = 0;

        while (startIndex < size) {
            int endIndex = Math.min(startIndex + 500, size);

            var splitRequest = new MultipleTargetsMessageBatchRequest(
                fcmTokens.subList(startIndex, endIndex),
                request.message()
            );
            splitRequests.add(splitRequest);

            startIndex = endIndex;
        }

        return splitRequests;
    }
}
