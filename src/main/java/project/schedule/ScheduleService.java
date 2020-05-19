package project.schedule;

import project.service.Service;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Optional<Schedule> getScheduleById(Long id);

    List<Schedule> getAllSchedule();

    void save(Schedule schedule);
    void deleteAll();
    void deleteScheduleById(Long id);
}
