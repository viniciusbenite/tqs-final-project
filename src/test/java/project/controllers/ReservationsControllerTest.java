package project.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import project.reservation.Reservation;
import project.reservation.ReservationController;
import project.reservation.ReservationRepository;
import project.reservation.ReservationServiceImp;
import project.service.Service;
import project.user.User;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationsControllerTest {

    @Mock( lenient = true)
    ReservationRepository R;

    @Mock( lenient = true)
    private Model model;


    @InjectMocks
    ReservationController controller;


    @Test
    public void getAllReservations() throws Exception {

        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");


        List<Reservation> allReservations = singletonList(reservation);



        Mockito.when(R.findAll()).thenReturn(allReservations);


        assertEquals(controller.all(),allReservations);

    }


    @Test
    public void newReservation() throws Exception {

        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");





        Mockito.when(R.save(reservation)).thenReturn(reservation);


        assertEquals(controller.newReservation(reservation),reservation);

    }


    @Test
    public void getReservation() throws Exception {

        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");






        Mockito.when(R.getReservationById(reservation.getId())).thenReturn(java.util.Optional.of(reservation));


        assertEquals(controller.getReservation(reservation.getId()),reservation);

    }

    @Test
    public void editReservation() throws Exception {

        Reservation reservation = new Reservation();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Service service = new Service();
        reservation.setId(1L);
        reservation.setUser(user);
        reservation.setService(service);
        reservation.setTime("12:30");
        reservation.setDate("12/04/2020");


        reservation.setUsers(user);
        assertEquals(user,reservation.getUsers());
        reservation.setService(service);
        assertEquals(service,reservation.getService());
        reservation.setId(reservation.getId());
        assertEquals(reservation.getId(),reservation.getId());
        reservation.setTime(reservation.getTime());
        assertEquals(reservation.getTime(),reservation.getTime());
        reservation.setDate(reservation.getDate());
        assertEquals(reservation.getDate(),reservation.getDate());






        Mockito.when(R.getReservationById(reservation.getId())).thenReturn(java.util.Optional.of(reservation));
        Mockito.when(R.save(reservation)).thenReturn(reservation);


        assertEquals(controller.editReservation(reservation,reservation.getId()),reservation);

    }




}