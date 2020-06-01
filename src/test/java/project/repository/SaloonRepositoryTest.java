package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.saloon.Saloon;
import project.saloon.SaloonRepository;
import project.schedule.Schedule;
import project.service.Service;
import project.user.User;
import project.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class SaloonRepositoryTest {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findByNameContainsIgnoreCase() {
        Saloon test = new Saloon();
        test.setName("saloon");
        User owner = new User();

        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        testEntityManager.persistAndFlush(test);
        Saloon found = saloonRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(found.getName() ,test.getName());
    }

    @Test
    void findAll() {
        Saloon test = new Saloon();
        Saloon test2 = new Saloon();
        List<Saloon> all = new ArrayList<Saloon>();
        User owner = new User();
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        test2.setOwner(owner);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);

        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Saloon> found = saloonRepository.findAll();

        assertEquals(found , all);
    }

    @Test
    void getSaloonById() {
        Saloon test = new Saloon();
        User owner = new User();
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Saloon found = saloonRepository.getSaloonById(id).get();

        assertEquals(found.getId() , id);
    }

    @Test
    void deleteAll() {
        Saloon test = new Saloon();
        Saloon test2 = new Saloon();

        User owner = new User();
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        test2.setOwner(owner);

        List<Saloon> all = new ArrayList<>();
        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Saloon> found = saloonRepository.findAll();

        assertEquals(found , all);

        saloonRepository.deleteAll();
        assertTrue(saloonRepository.findAll().isEmpty());
    }

    @Test
    void deleteByName() {
        Saloon test = new Saloon();
        test.setName("saloon");
        User owner = new User();
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        testEntityManager.persistAndFlush(test);
        Saloon found = saloonRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(test.getName() , found.getName() );

        saloonRepository.deleteByName(test.getName());
        assertNull(saloonRepository.findByNameContainsIgnoreCase(test.getName()));
    }
}