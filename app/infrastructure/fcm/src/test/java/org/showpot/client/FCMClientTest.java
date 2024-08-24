package org.showpot.client;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Notification;
import org.example.message.PushMessageTemplate;
import org.junit.jupiter.api.Test;
import org.showpot.dto.param.FCMMessageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("fcm-test")
@Testcontainers
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FCMClientTest {

    @Autowired
    private FCMClient fcmClient;

    @Test
    public void send() throws FirebaseMessagingException {
        FCMMessageParam messageParam = FCMMessageParam.builder()
            .title("title")
            .body("body")
            .imageURL("abc")
            .build();

        fcmClient.sendNotification(
            "dlOUiud4RzKTGYjX1PzmmT:APA91bG2pt3AwwIEW2gwIwgCfQ6YdVehulGCgIsnXrIK2CG8Nphvg8iCsWI7qnxphgFUn-RqfT7N2y2AYvv8NtNjLRav430ont8lzUF8l_Sj7ZihTKt6ulckbhoXq41XAu8vBRdTuB6r",
            messageParam.toNotification()
        );
    }
}
