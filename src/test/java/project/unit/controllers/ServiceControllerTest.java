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
import project.service.ServiceController;
import project.user.User;

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
import static project.constants.Paths.SERVICE;
import static project.unit.controllers.ReservationControllerTest.asJsonString;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

    User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
    Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
            "Portugal", "open", "barbeiro", "12345",
            "blabla", "someimage", "endereço", user);

    Service service = new Service();
    Schedule schedule = new Schedule();

    List<Service> allService = singletonList(service);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceController serviceController;

    @Before
    public void setup() {

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");

        schedule.setId(1L);
        schedule.setSallon(saloon);
        schedule.setService(service);
    }


    @Test
    public void getAllServices() throws Exception {
        /*
            Check GET all method
        */
        given(serviceController.all()).willReturn(allService);

        mockMvc.perform(get(SERVICE)
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) service.getId())));
    }

    @Test
    public void getSingleService() throws Exception {
        /*
            Check GET {ID} method
        */
        given(serviceController.getService(service.getId())).willReturn(service);

        mockMvc.perform(get(SERVICE + "/" + service.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) service.getId())));
    }

    @Test
    public void createNewService() throws Exception {
        /*
            Create new service test (POST)
        */
        given(serviceController.getService(service.getId())).willReturn(service);

        mockMvc.perform(post(SERVICE + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(service)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteServiceTest() throws Exception {
        /*
            Delete a single service test
        */
        given(serviceController.getService(service.getId())).willReturn(service);

        mockMvc.perform(delete(SERVICE + "/" + service.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(service)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllServiceTest() throws Exception {
        /*
            Delete all service test
        */
        given(serviceController.all()).willReturn(allService);

        mockMvc.perform(delete(SERVICE + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(service)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400WhenStringAsArgumentTest() throws Exception {
        /*
            the call must accepts only longs/int as argument
        */

        mockMvc.perform(get(SERVICE + "/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
