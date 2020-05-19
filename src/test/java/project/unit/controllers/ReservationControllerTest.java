package project.unit.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Time;

import project.reservation.Reservation;
import project.reservation.ReservationController;
import project.service.Service;
import project.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
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

import static project.constants.Paths.RESERVATION;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationController reservationController;

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
        reservation.setTime(Time.valueOf(LocalTime.now()));
        reservation.setDate(Date.valueOf(LocalDate.now()));
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
    public void getSingleReservation() throws Exception {
/*
            Check GET {ID} method
        */
        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime(Time.valueOf(LocalTime.now()));
        reservation.setDate(Date.valueOf(LocalDate.now()));
        System.out.println(reservation.getId());

        given(reservationController.getReservation(reservation.getId())).willReturn(reservation);

        mockMvc.perform(get(RESERVATION + "/" + reservation.getId())
                .with(user("Fulano de Tal").password("somepass"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is((int) (long) reservation.getId())));
    }
}
