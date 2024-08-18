package org.example.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.example.listener.dto.TicketingReservationMessageApiRequest;
import org.springframework.data.redis.connection.Message;

@Slf4j
public class TicketingMessageConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static TicketingReservationMessageApiRequest toTicketingReservationMessage(Message message) {
        try {
            var convertedMessage = objectMapper.readValue(
                message.getBody(),
                TicketingReservationMessageApiRequest.class
            );
            log.info("Message published successfully to topic: {}",
                new String(message.getChannel()));
            log.info(
                "Subscribe Message Contents ( TicketingReservationMessageApiRequest : {} )",
                message);

            return convertedMessage;
        } catch (IOException e) {
            log.error("Failed to convert message to TicketingReservationMessageApiRequest", e);
            throw new IllegalArgumentException("메시지를 받지 못했습니다.");
        }
    }

}
