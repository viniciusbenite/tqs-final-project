package project.schedule;

import project.service.Service;

import java.util.List;

public interface ScheduleService {

    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedule();

    void save(Schedule schedule);
    void deleteAll();
    void deleteScheduleById(Long id);
}
