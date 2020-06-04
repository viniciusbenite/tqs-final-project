package project.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.saloon.Saloon;
import project.saloon.SaloonController;
import project.service.Service;
import project.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.Paths.SALOON;
import static project.controllers.ReservationControllerITTest.asJsonString;

@RunWith(SpringRunner.class)
@WebMvcTest(SaloonController.class)
public class SaloonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaloonController saloonController;

    @Test
    public void getAllSaloons() throws Exception {
        /*
            Check GET all method
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        List<Saloon> allSaloons = singletonList(saloon);

        given(saloonController.all()).willReturn(allSaloons);

        mockMvc.perform(get(SALOON)
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) saloon.getId())));
    }

    @Test
    public void getSingleSaloon() throws Exception {
        /*
            Check GET {ID} method
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        given(saloonController.getSaloon(saloon.getId())).willReturn(saloon);

        mockMvc.perform(get(SALOON + "/" + saloon.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) saloon.getId())));
    }

    @Test
    public void createNewSaloon() throws Exception {
        /*
            Create new saloon test POST
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setServices(services);

        given(saloonController.getSaloon(saloon.getId())).willReturn(saloon);

        mockMvc.perform(post(SALOON + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(saloon)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSaloonTest() throws Exception {
        /*
            Delete a single saloon test
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        saloon.setId(1L);
        given(saloonController.getSaloon(saloon.getId())).willReturn(saloon);

        mockMvc.perform(delete(SALOON + "/" + saloon.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(saloon)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllSaloonTest() throws Exception {
        /*
            Delete all saloons test
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        List<Saloon> allSaloons = singletonList(saloon);
        given(saloonController.all()).willReturn(allSaloons);

        mockMvc.perform(delete(SALOON + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(saloon)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400WhenStringAsArgumentTest() throws Exception {
        /*
            the call must accepts only longs/int as argument
        */
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        mockMvc.perform(get(SALOON + "/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
