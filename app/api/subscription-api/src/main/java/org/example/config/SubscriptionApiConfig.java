package org.example.config;

import lombok.RequiredArgsConstructor;
import org.example.listener.RegisterShowMessageLister;
import org.example.listener.SubscriptionArtistMessageLister;
import org.example.listener.UpdateShowMessageLister;
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
    RegisterShowMessageLister.class,
    UpdateShowMessageLister.class,
    SubscriptionArtistMessageLister.class
})
@ComponentScan(basePackages = "org.example")
@RequiredArgsConstructor
public class SubscriptionApiConfig {

    private final MessageListener registerShowMessageLister;
    private final MessageListener updateShowMessageLister;
    private final MessageListener subscriptionArtistMessageLister;

    @Bean
    MessageListenerAdapter registerShowMessageListenerAdapter() {
        return new MessageListenerAdapter(registerShowMessageLister);
    }

    @Bean
    MessageListenerAdapter updateShowMessageListenerAdapter() {
        return new MessageListenerAdapter(updateShowMessageLister);
    }

    @Bean
    MessageListenerAdapter subscriptionArtistMessageListerAdapter() {
        return new MessageListenerAdapter(subscriptionArtistMessageLister);
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

}
