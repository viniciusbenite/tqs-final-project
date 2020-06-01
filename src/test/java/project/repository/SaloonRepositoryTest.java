package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.saloon.Saloon;
import project.saloon.SaloonRepository;
import project.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class SaloonRepositoryTest {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findByNameContainsIgnoreCase() {
        User owner = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon test = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        test.setName("saloon");

        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        testEntityManager.persistAndFlush(test);
        Saloon found = saloonRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(found.getName(), test.getName());
    }

    @Test
    void findAll() {
        User owner = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon test = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        test.setName("saloon");
        Saloon test2 = new Saloon("Saloon name 2", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        List<Saloon> all = new ArrayList<Saloon>();
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
        User owner = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon test = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Saloon found = saloonRepository.getSaloonById(id).get();

        assertEquals(found.getId(), id);
    }

    @Test
    void deleteAll() {
        User owner = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon test = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        Saloon test2 = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
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
        User owner = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon test = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", owner);
        test.setName("saloon");
        testEntityManager.persistAndFlush(owner);
        test.setOwner(owner);
        testEntityManager.persistAndFlush(test);
        Saloon found = saloonRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(test.getName(), found.getName());

        saloonRepository.deleteByName(test.getName());
        assertNull(saloonRepository.findByNameContainsIgnoreCase(test.getName()));
    }
}