package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.listener.RegisterShowMessageListener;
import org.example.listener.SubscriptionArtistMessageListener;
import org.example.listener.SubscriptionGenreMessageListener;
import org.example.listener.UnsubscriptionArtistMessageListener;
import org.example.listener.UnsubscriptionGenreMessageListener;
import org.example.listener.UpdateShowMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@Import({
    SubscriptionDomainConfig.class,
    PubSubConfig.class,
    RegisterShowMessageListener.class,
    UpdateShowMessageListener.class,
    SubscriptionArtistMessageListener.class,
    UnsubscriptionArtistMessageListener.class,
    SubscriptionGenreMessageListener.class,
    UnsubscriptionGenreMessageListener.class
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
        return container;
    }

}
