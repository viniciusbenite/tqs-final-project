package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.user.User;
import project.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findByNameContainsIgnoreCase() {
        User test = new User("test","test@email.com", "pass");
        testEntityManager.persistAndFlush(test);
        User found = userRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(found.getName() ,test.getName());
    }

    @Test
    void findAll() {
        User test = new User("test","test@email.com", "pass");
        User test2 = new User("test2","test@email.com", "pass");
        List<User> all = new ArrayList<>();
        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<User> found = userRepository.findAll();

        assertEquals(found , all);

    }

    @Test
    void getUserById() {
        User test = new User("test","test@email.com", "pass");
        Long id = (Long) testEntityManager.persistAndGetId(test);

        User found = userRepository.getUserById(id).get();

        assertEquals(found.getId() , id);
    }

    @Test
    void deleteAll() {
        User test = new User("test","test@email.com", "pass");
        testEntityManager.persistAndFlush(test);

        userRepository.deleteAll();
        assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    void deleteByName() {
        User test = new User("test","test@email.com", "pass");
        testEntityManager.persistAndFlush(test);
        User found = userRepository.findByNameContainsIgnoreCase(test.getName());

        assertEquals(test.getName() , found.getName() );

        userRepository.deleteByName(test.getName());
        assertNull(userRepository.findByNameContainsIgnoreCase(test.getName()));

    }
}