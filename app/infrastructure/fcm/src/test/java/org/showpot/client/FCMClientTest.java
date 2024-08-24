package org.showpot.client;

import com.google.firebase.messaging.FirebaseMessagingException;
import java.util.stream.Stream;
import org.example.message.MessageParam;
import org.example.message.PushMessageTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest
    @MethodSource("getMessage")
    public void send(MessageParam message) throws FirebaseMessagingException {
        FCMMessageParam fcmMessage = FCMMessageParam.from(message);

        fcmClient.sendNotification(
            "cLGIPRQUS8WYOtGNiN0_jz:"
                + "APA91bGBmeu5-"
                + "A2aXvbrg8Kssnsh9KgmY0Z6miJB2Fa3E-"
                + "g2JWGcbUNYaeJTSTgFoid9f9wYx3zZ40VMuQoYTPdroj3gtzb7cmALuDCOROakttyIr7wCVTbFEFJlTDPVNOF1uPD7Te1U",
            fcmMessage.toNotification()
        );
    }

    private static Stream<Arguments> getMessage() {
        return Stream.of(
            Arguments.of(PushMessageTemplate.getTicketingAlertMessageBefore24Hours("공연제목")),
            Arguments.of(PushMessageTemplate.getTicketingAlertMessageBefore6Hours("공연제목")),
            Arguments.of(PushMessageTemplate.getTicketingAlertMessageBefore1Hours("공연제목")),
            Arguments.of(PushMessageTemplate.getSubscribedArtistVisitKoreaAlertMessage("아티스트이름")),
            Arguments.of(PushMessageTemplate.getSubscribedGenreVisitKoreaAlertMessage("장르이름"))
        );
    }
}
