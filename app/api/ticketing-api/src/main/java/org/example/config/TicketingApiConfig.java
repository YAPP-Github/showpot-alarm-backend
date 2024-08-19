package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ErrorHandler;

@Configuration
@Import({
    PubSubConfig.class,
    TicketingDomainConfig.class,
})
@ComponentScan(basePackages = "org.example")
@RequiredArgsConstructor
public class TicketingApiConfig {

    private final MessageListener ticketingAlertMessageListener;
    private final ErrorHandler redisSubErrorHandler;

    @Bean
    MessageListenerAdapter ticketingAlertMessageListenerAdapter() {
        return new MessageListenerAdapter(ticketingAlertMessageListener);
    }

    @Bean
    RedisMessageListenerContainer ticketingAlertMessageListenerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter ticketingAlertMessageListenerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(
            ticketingAlertMessageListenerAdapter,
            ChannelTopic.of("ticketingAlert")
        );
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }
}
