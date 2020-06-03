package project.schedule;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @ApiOperation("Get all schedule")
    @GetMapping
    public List<Schedule> all() {
        return scheduleService.getAllSchedule();
    }

    @ApiOperation("Create a new schedule")
    @PostMapping("/")
    public Schedule newSchedule(@RequestBody Schedule newSchedule) {
        return scheduleRepository.save(newSchedule);
    }

    @ApiOperation("Get a single schedule")
    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Long id) {
        return scheduleRepository.getScheduleById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    @ApiOperation("Edit a schedule")
    @PutMapping("/{id}")
    public Schedule editSchedule(@RequestBody Schedule newSchedule,
                                 @PathVariable Long id) {
        return scheduleRepository.findById(id)
                .map(schedule -> {
                    schedule.setSallon(newSchedule.getSallon());
                    schedule.setService(newSchedule.getService());
                    schedule.setStartTime(newSchedule.getStartTime());
                    schedule.setEndTime(newSchedule.getEndTime());
                    schedule.setId(newSchedule.getId());
                    schedule.setSallon(newSchedule.getSallon());
                    schedule.setService(newSchedule.getService());
                    return scheduleRepository.save(schedule);
                })
                .orElseGet(() -> {
                    newSchedule.setId(id);
                    return scheduleRepository.save(newSchedule);
                });
    }

    @ApiOperation("Delete a schedule")
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleRepository.deleteById(id);
    }

    @ApiOperation("Delete all schedule")
    @DeleteMapping("/")
    public void purgeSchedule() {
        scheduleRepository.deleteAll();
    }
}
