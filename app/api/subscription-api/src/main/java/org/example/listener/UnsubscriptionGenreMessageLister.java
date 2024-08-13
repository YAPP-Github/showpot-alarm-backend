package org.example.listener;

import lombok.RequiredArgsConstructor;
import org.example.converter.MessageConverter;
import org.example.service.SubscriptionAlarmService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Qualifier(value = "unsubscriptionGenreMessageLister")
public class UnsubscriptionGenreMessageLister implements MessageListener {

    private final SubscriptionAlarmService subscriptionAlarmService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        var request = MessageConverter.toGenreSubscriptionMessage(message);
        subscriptionAlarmService.genreUnsubscribe(request.toServiceRequest());
    }

}
