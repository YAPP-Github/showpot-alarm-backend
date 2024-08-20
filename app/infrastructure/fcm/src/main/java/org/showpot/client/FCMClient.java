package org.showpot.client;

import com.google.firebase.messaging.FirebaseMessaging;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.showpot.client.dto.request.SendFCMMessageClientRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMClient {

    private final FirebaseMessaging firebaseMessaging;

    public void sendNotification(SendFCMMessageClientRequest request) {
        var fcmTokens = List.of("token1");
    }
}
