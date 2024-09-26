package org.example.repository.alarm;

import java.util.UUID;
import org.example.entity.ShowAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowAlarmRepository extends JpaRepository<ShowAlarm, UUID> {

}
