package org.example.listener;

import lombok.RequiredArgsConstructor;
import org.example.converter.TicketingMessageConverter;
import org.example.service.TicketingAlertService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Qualifier(value = "ticketingAlertMessageListener")
public class TicketingAlertMessageListener implements MessageListener {

    private final TicketingAlertService ticketingAlertService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        var request = TicketingMessageConverter.toTicketingReservationMessage(message);
        ticketingAlertService.reserveTicketingAlert(request.toServiceRequest());
    }
}
