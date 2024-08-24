package org.showpot.client;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.Test;
import org.showpot.dto.param.FCMMessageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("fcm-local")
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
            .build();

        fcmClient.sendNotification(
            "cLGIPRQUS8WYOtGNiN0_jz:"
                + "APA91bGBmeu5-"
                + "A2aXvbrg8Kssnsh9KgmY0Z6miJB2Fa3E-"
                + "g2JWGcbUNYaeJTSTgFoid9f9wYx3zZ40VMuQoYTPdroj3gtzb7cmALuDCOROakttyIr7wCVTbFEFJlTDPVNOF1uPD7Te1U",
            messageParam.toNotification()
        );
    }
}
