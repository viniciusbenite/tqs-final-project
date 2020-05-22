package project.unit.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import project.saloon.Saloon;
import project.schedule.Schedule;
import project.schedule.ScheduleController;
import project.service.Service;
import project.user.User;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static project.constants.Paths.SCHEDULE;
import static project.unit.controllers.ReservationControllerTest.asJsonString;

@RunWith(SpringRunner.class)
@WebMvcTest(ScheduleController.class)
public class ScheduleControllerTest {

    User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
    Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
            "Portugal", "open", "barbeiro", "12345",
            "blabla", "someimage", "endereço", user);

    Service service = new Service();
    Schedule schedule = new Schedule();

    List<Schedule> allSchedule = singletonList(schedule);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleController scheduleController;

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
    public void getAllSchedule() throws Exception {
        /*
            Check GET all method
        */
        given(scheduleController.all()).willReturn(allSchedule);

        mockMvc.perform(get(SCHEDULE)
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) schedule.getId())));
    }

    @Test
    public void getSingleSchedule() throws Exception {
        /*
            Check GET {ID} method
        */
        given(scheduleController.getSchedule(schedule.getId())).willReturn(schedule);

        mockMvc.perform(get(SCHEDULE + "/" + schedule.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) schedule.getId())));
    }

    @Test
    public void createNewSchedule() throws Exception {
        /*
            Create new schedule test (POST)
        */
        given(scheduleController.getSchedule(schedule.getId())).willReturn(schedule);

        mockMvc.perform(post(SCHEDULE + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(schedule)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteScheduleTest() throws Exception {
        /*
            Delete a single schedule test
        */
        given(scheduleController.getSchedule(schedule.getId())).willReturn(schedule);

        mockMvc.perform(delete(SCHEDULE + "/" + schedule.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(schedule)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllScheduleTest() throws Exception {
        /*
            Delete all schedule test
        */
        given(scheduleController.all()).willReturn(allSchedule);

        mockMvc.perform(delete(SCHEDULE + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(schedule)))
                .andExpect(status().isOk());
    }
}
