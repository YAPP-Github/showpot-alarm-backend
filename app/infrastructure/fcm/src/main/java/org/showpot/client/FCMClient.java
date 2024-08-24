package org.showpot.client;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FCMClient {

    private final FirebaseMessaging firebaseMessaging;

    public void sendNotification(String fcmToken, Notification notification) throws FirebaseMessagingException {
        firebaseMessaging.send(
            Message.builder()
                .setToken(fcmToken)
                .setNotification(notification)
                .build()
        );
    }
}
