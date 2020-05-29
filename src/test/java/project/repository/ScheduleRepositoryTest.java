package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.schedule.Schedule;
import project.schedule.ScheduleRepository;
import project.service.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAll() {
        Schedule test = new Schedule();
        Schedule test2 = new Schedule();
        List<Schedule> all = new ArrayList<Schedule>();

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);

        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Schedule> found = scheduleRepository.findAll();

        assertEquals(found , all);
    }

    @Test
    void getScheduleById() {
        Schedule test = new Schedule();
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Schedule found = scheduleRepository.getScheduleById(id).get();

        assertEquals(found.getId() , id);
    }

    @Test
    void deleteAll() {
        Schedule test = new Schedule();
        Schedule test2 = new Schedule();
        List<Schedule> all = new ArrayList<>();
        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Schedule> found = scheduleRepository.findAll();

        assertEquals(found , all);

        scheduleRepository.deleteAll();
        assertTrue(scheduleRepository.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Schedule test = new Schedule();
        testEntityManager.persistAndFlush(test);
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Schedule found = scheduleRepository.getScheduleById(id).get();

        assertEquals(id , found.getId() );

        scheduleRepository.deleteById(id);
        assertFalse(scheduleRepository.getScheduleById(id).isPresent());
    }
}