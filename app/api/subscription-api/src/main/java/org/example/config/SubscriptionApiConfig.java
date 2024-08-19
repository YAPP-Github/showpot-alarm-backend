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
    SubscriptionDomainConfig.class,
    PubSubConfig.class
})
@ComponentScan(basePackages = "org.example")
@RequiredArgsConstructor
public class SubscriptionApiConfig {

    private final MessageListener registerShowMessageListener;
    private final MessageListener updateShowMessageListener;
    private final MessageListener subscriptionArtistMessageListener;
    private final MessageListener unsubscriptionArtistMessageListener;
    private final MessageListener subscriptionGenreMessageListener;
    private final MessageListener unsubscriptionGenreMessageListener;
    private final ErrorHandler redisSubErrorHandler;


    @Bean
    MessageListenerAdapter registerShowMessageListenerAdapter() {
        return new MessageListenerAdapter(registerShowMessageListener);
    }

    @Bean
    MessageListenerAdapter updateShowMessageListenerAdapter() {
        return new MessageListenerAdapter(updateShowMessageListener);
    }

    @Bean
    MessageListenerAdapter subscriptionArtistMessageListerAdapter() {
        return new MessageListenerAdapter(subscriptionArtistMessageListener);
    }

    @Bean
    MessageListenerAdapter unsubscriptionArtistMessageListerAdapter() {
        return new MessageListenerAdapter(unsubscriptionArtistMessageListener);
    }

    @Bean
    MessageListenerAdapter subscriptionGenreMessageListerAdapter() {
        return new MessageListenerAdapter(subscriptionGenreMessageListener);
    }

    @Bean
    MessageListenerAdapter unsubscriptionGenreMessageListerAdapter() {
        return new MessageListenerAdapter(unsubscriptionGenreMessageListener);
    }

    @Bean
    RedisMessageListenerContainer registerShowMessageListenerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter registerShowMessageListenerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(registerShowMessageListenerAdapter,
            ChannelTopic.of("registerShow"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

    @Bean
    RedisMessageListenerContainer updateShowMessageListenerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter updateShowMessageListenerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(updateShowMessageListenerAdapter,
            ChannelTopic.of("updateShow"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

    @Bean
    RedisMessageListenerContainer subscriptionArtistMessageListerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter subscriptionArtistMessageListerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriptionArtistMessageListerAdapter,
            ChannelTopic.of("artistSubscription"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

    @Bean
    RedisMessageListenerContainer unsubscriptionArtistMessageListerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter unsubscriptionArtistMessageListerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(unsubscriptionArtistMessageListerAdapter,
            ChannelTopic.of("artistUnsubscription"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

    @Bean
    RedisMessageListenerContainer subscriptionGenreMessageListerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter subscriptionGenreMessageListerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(subscriptionGenreMessageListerAdapter,
            ChannelTopic.of("genreSubscription"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

    @Bean
    RedisMessageListenerContainer unsubscriptionGenreMessageListerContainer(
        RedisConnectionFactory connectionFactory,
        MessageListenerAdapter unsubscriptionGenreMessageListerAdapter
    ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(unsubscriptionGenreMessageListerAdapter,
            ChannelTopic.of("genreUnsubscription"));
        container.setErrorHandler(redisSubErrorHandler);
        return container;
    }

}
