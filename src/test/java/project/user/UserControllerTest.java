package project.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.Paths.USER;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;


    @Test
    public void getSingleUser() throws Exception {
        /*
            Check GET {ID} method
        */
        User user = new User("test", "test@gmail.com", "pass");
        user.setId(1L);

        given(userController.getUser(user.getId())).willReturn(user);

        mockMvc.perform(get(USER + "/" + user.getId())
                .with(user("test").password("pass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) user.getId())));
    }


    @Test
    public void getAllUsers() throws Exception {
        /*
            Check GET all method
        */
        User user = new User("test", "test@gmail.com", "pass");
        user.setId(1L);

        List<User> allUsers = singletonList(user);

        given(userController.all()).willReturn(allUsers);

        mockMvc.perform(get(USER)
                .with(user("test").password("pass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) user.getId())));
    }

}