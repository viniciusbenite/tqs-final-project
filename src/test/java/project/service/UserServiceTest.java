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
import project.user.User;
import project.user.UserRepository;
import project.user.UserService;
import project.user.UserServiceImp;
import org.mockito.internal.verification.VerificationModeFactory;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration()
public class UserServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImp();
        }
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @InjectMocks
    private UserServiceImp userServiceI;

    @Before
    public void setUp() {
        User test = new User("test","test@email.com", "pass");
        User test2 = new User("test2", "test2@email.com", "pass2");

        List<User> all = new ArrayList<User>();
        all.add(test);
        all.add(test2);

        when(userRepository.findByNameContainsIgnoreCase(test.getName())).thenReturn(test);
        when(userRepository.findByNameContainsIgnoreCase(test2.getName())).thenReturn(test2);
        when(userRepository.findAll()).thenReturn(all);
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

    }


    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "test";
        User found = userService.getUserByName(name);
        assertEquals(found.getName(),name);
    }

    @Test
    public void whenInValidName_thenEmployeeShouldNotBeFound() {
        User fromDb = userService.getUserByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }


    @Test
    public void all() {
        User test = new User("test","test@email.com", "pass");
        User test2 = new User("test2", "test2@email.com", "pass2");

        List<User> all = new ArrayList<User>();
        all.add(test);
        all.add(test2);

        List <User> serviceAllUser = userService.getAllUser();

        assertEquals(all, serviceAllUser);
    }


    @Test
    public void save(){
        User created = new User("created","test@email.com", "pass");
        User saved = userRepository.save(created);

        assertEquals(created.getName() , saved.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findByNameContainsIgnoreCase(name);
        Mockito.reset(userRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(userRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(userRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(userRepository);
    }
}