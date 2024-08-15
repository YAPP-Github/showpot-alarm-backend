package org.example.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.example.listener.dto.ArtistSubscriptionMessageApiRequest;
import org.example.listener.dto.GenreSubscriptionMessageApiRequest;
import org.example.listener.dto.ShowRelationSubscriptionMessageApiRequest;
import org.springframework.data.redis.connection.Message;

@Slf4j
public class SubscriptionMessageConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ShowRelationSubscriptionMessageApiRequest toShowRelationSubscriptionMessage(
        Message message) {
        try {
            var convertedMessage = objectMapper.readValue(
                message.getBody(),
                ShowRelationSubscriptionMessageApiRequest.class
            );
            log.info("Message published successfully to topic: {}",
                new String(message.getChannel()));
            log.info(
                "Subscribe Message Contents ( ShowRelationSubscriptionMessageApiRequest : {} )",
                message);

            return convertedMessage;
        } catch (IOException e) {
            log.error("Failed to convert message to ShowRelationSubscriptionMessageApiRequest", e);
            throw new IllegalArgumentException("메시지를 받지 못했습니다.");
        }
    }

    public static ArtistSubscriptionMessageApiRequest toArtistSubscriptionMessage(Message message) {
        try {
            var convertedMessage = objectMapper.readValue(
                message.getBody(),
                ArtistSubscriptionMessageApiRequest.class
            );
            log.info("Message published successfully to topic: {}",
                new String(message.getChannel()));
            log.info("Subscribe Message Contents ( ArtistSubscriptionMessageApiRequest : {} )",
                message);

            return convertedMessage;
        } catch (IOException e) {
            log.error("Failed to convert message to ArtistSubscriptionMessageApiRequest", e);
            throw new IllegalArgumentException("메시지를 받지 못했습니다.");
        }
    }

    public static GenreSubscriptionMessageApiRequest toGenreSubscriptionMessage(Message message) {
        try {
            var convertedMessage = objectMapper.readValue(
                message.getBody(),
                GenreSubscriptionMessageApiRequest.class
            );
            log.info("Message published successfully to topic: {}",
                new String(message.getChannel()));
            log.info("Subscribe Message Contents ( GenreSubscriptionMessageServiceRequest : {} )",
                message);

            return convertedMessage;
        } catch (IOException e) {
            log.error("Failed to convert message to GenreSubscriptionMessageServiceRequest", e);
            throw new IllegalArgumentException("메시지를 받지 못했습니다.");
        }
    }
}
