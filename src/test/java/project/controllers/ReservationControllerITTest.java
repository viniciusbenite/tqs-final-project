package project.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.reservation.Reservation;
import project.reservation.ReservationController;
import project.reservation.ReservationRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.Paths.RESERVATION;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationController reservationController;

    @MockBean
    private ReservationRepository reservationRepository;

    @Test
    public void getAllReservations() throws Exception {
        /*
            Check GET all method
        */
        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");
        System.out.println(reservation.getId());

        List<Reservation> allReservations = singletonList(reservation);

        given(reservationController.all()).willReturn(allReservations);

        mockMvc.perform(get(RESERVATION)
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is((int) (long) reservation.getId())));
    }

    @Test
    public void getSingleReservationTest() throws Exception {
        /*
            Check GET {ID} method
        */
        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");

        given(reservationController.getReservation(reservation.getId())).willReturn(reservation);

        mockMvc.perform(get(RESERVATION + "/" + reservation.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) reservation.getId())));
    }

    @Test
    public void createReservationTest() throws Exception {
        /*
            Create a new reservation test
        */
        Reservation newReservation = new Reservation();

        given(reservationController.getReservation(newReservation.getId())).willReturn(newReservation);

        mockMvc.perform(post(RESERVATION + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(newReservation)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteReservationTest() throws Exception {
        /*
            Delete a single reservation test
        */
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        given(reservationController.getReservation(reservation.getId())).willReturn(reservation);

        mockMvc.perform(delete(RESERVATION + "/" + reservation.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAllReservationTest() throws Exception {
        /*
            Delete all reservations test
        */
        Reservation reservation = new Reservation();

        List<Reservation> allReservations = singletonList(reservation);

        given(reservationController.all()).willReturn(allReservations);

        mockMvc.perform(delete(RESERVATION + "/")
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON)
                .content(asJsonString(reservation)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400WhenStringAsArgumentTest() throws Exception {
        /*
            the call must accepts only longs/int as argument
        */
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        mockMvc.perform(get(RESERVATION + "/hello")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // Auxiliar functions
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
