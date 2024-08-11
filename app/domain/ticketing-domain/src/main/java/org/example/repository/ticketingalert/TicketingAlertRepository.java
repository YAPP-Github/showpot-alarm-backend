package org.example.repository.ticketingalert;

import java.util.UUID;
import org.example.entity.TicketingAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketingAlertRepository extends JpaRepository<TicketingAlert, UUID> {

}
