package project.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.getScheduleById(id);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteAll() {
        scheduleRepository.deleteAll();
    }

    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
