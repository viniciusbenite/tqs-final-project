package project.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.Service;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("all")
    public List<Schedule> all(){
        return scheduleService.getAllSchedule();
    }


    @GetMapping("teste")
    public String teste(){
        Schedule a = new Schedule();
        scheduleService.save(a);
        return "teste feito";
    }


}
