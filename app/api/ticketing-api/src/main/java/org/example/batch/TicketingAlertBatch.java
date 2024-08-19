package org.example.batch;

import org.example.service.dto.response.TicketingAlertServiceResponse;

public interface TicketingAlertBatch {

    void reserveTicketingAlerts(TicketingAlertServiceResponse ticketingAlert);

}
