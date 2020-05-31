package project.unit.controllers;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.saloon.Saloon;
import project.schedule.Schedule;
import project.service.Service;
import project.user.User;
import project.user.UserController;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.Paths.USER;
import static project.unit.controllers.ReservationControllerTest.asJsonString;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
    Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
            "Portugal", "open", "barbeiro", "12345",
            "blabla", "someimage", "endereço", user);

    Service service = new Service();
    Schedule schedule = new Schedule();

    List<User> allUsers = singletonList(user);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController userController;

    @Before
    public void setup() {

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");

        schedule.setId(1L);
        schedule.setSallon(saloon);
        schedule.setService(service);

        user.setId(1111L);
    }


    @Test
    public void getAllUsers() throws Exception {
        /*
            Check GET all method
        */
        given(userController.all()).willReturn(allUsers);

        mockMvc.perform(get(USER)
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) user.getId())));
    }

    @Test
    public void getSingleUser() throws Exception {
        /*
            Check GET {ID} method
        */
        given(userController.getUser(user.getId())).willReturn(user);

        mockMvc.perform(get(USER + "/" + user.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) user.getId())));
    }

    @Test
    public void createNewService() throws Exception {
        /*
            Create new service test (POST)
        */
        given(userController.getUser(user.getId())).willReturn(user);

        mockMvc.perform(post(USER + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {
        /*
            Delete a single user test
        */
        given(userController.getUser(user.getId())).willReturn(user);

        mockMvc.perform(delete(USER + "/" + user.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllUsersTest() throws Exception {
        /*
            Delete all user test
        */
        given(userController.getUser(user.getId())).willReturn(user);

        mockMvc.perform(delete(USER + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400WhenStringAsArgumentTest() throws Exception {
        /*
            the call must accepts only longs/int as argument
        */

        mockMvc.perform(get(USER + "/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
