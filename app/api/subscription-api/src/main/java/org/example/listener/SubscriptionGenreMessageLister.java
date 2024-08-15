package org.example.listener;

import lombok.RequiredArgsConstructor;
import org.example.converter.SubscriptionMessageConverter;
import org.example.service.SubscriptionAlarmService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Qualifier(value = "subscriptionGenreMessageLister")
public class SubscriptionGenreMessageLister implements MessageListener {

    private final SubscriptionAlarmService subscriptionAlarmService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        var request = SubscriptionMessageConverter.toGenreSubscriptionMessage(message);
        subscriptionAlarmService.genreSubscribe(request.toServiceRequest());
    }

}
