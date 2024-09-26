package org.example.usecase;

import lombok.RequiredArgsConstructor;
import org.example.entity.ShowAlarm;
import org.example.repository.alarm.ShowAlarmRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowAlarmUseCase {

    private final ShowAlarmRepository showAlarmRepository;

    public void save(ShowAlarm showAlarm) {
        showAlarmRepository.save(showAlarm);
    }

}
