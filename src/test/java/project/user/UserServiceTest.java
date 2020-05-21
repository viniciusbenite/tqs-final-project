package project.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.reservation.ReservationController;
import project.saloon.SaloonController;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImp();
        }
    }

    @MockBean
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Before
    public void setUp() {
        User test = new User("test","test@email.com", "pass");

        Mockito.when(userRepository.findByNameContainsIgnoreCase(test.getName())).thenReturn(test);
    }


    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "test";
        User found = userService.getUserByName(name);
        assertEquals(found.getName(),name);
    }


    @Test
    public void all() {
    }
}