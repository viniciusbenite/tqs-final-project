package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import project.schedule.Schedule;
import project.schedule.ScheduleRepository;
import project.schedule.ScheduleService;
import project.schedule.ScheduleServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration()
public class ScheduleServiceTest {

    @TestConfiguration
    static class ScheduleServiceImplTestContextConfiguration {
        @Bean
        public ScheduleService scheduleService() {
            return new ScheduleServiceImp();
        }
    }

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Before
    public void setUp() {
        Schedule test = new Schedule();
        test.setId(10L);

        List<Schedule> all = new ArrayList<>();
        all.add(test);

        when(scheduleRepository.getScheduleById(10L)).thenReturn(Optional.of(test));
        when(scheduleRepository.findAll()).thenReturn(all);
        when(scheduleRepository.save(Mockito.any(Schedule.class))).thenAnswer(i -> i.getArguments()[0]);

    }

    @Test
    public void getScheduleById() {
        Long id = 10L;
        Optional<Schedule> found = scheduleService.getScheduleById(id);
        assertEquals(found.get().getId(),id);
    }

    @Test
    public void getAllSchedule() {
        Schedule test = new Schedule();
        test.setId(10L);

        List<Schedule> all = new ArrayList<>();
        all.add(test);

        List <Schedule> allSchedule = scheduleService.getAllSchedule();
        assertEquals(all, allSchedule);
    }

    @Test
    public void save() {
        Schedule created = new Schedule();
        created.setId(20L);
        Schedule saved = scheduleRepository.save(created);
        assertEquals(created.getId() , saved.getId());
    }
}