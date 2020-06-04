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
import project.reservation.Reservation;
import project.reservation.ReservationRepository;
import project.reservation.ReservationService;
import project.reservation.ReservationServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration()
public class ReservationServiceTest {

    @TestConfiguration
    static class ReservationServiceImplTestContextConfiguration {
        @Bean
        public ReservationService reservationService() {
            return new ReservationServiceImp();
        }
    }

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Before
    public void setUp() {
        Reservation test = new Reservation();
        test.setId(10L);

        List<Reservation> all = new ArrayList<>();
        all.add(test);

        when(reservationRepository.getReservationById(10L)).thenReturn(Optional.of(test));
        when(reservationRepository.findAll()).thenReturn(all);
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenAnswer(i -> i.getArguments()[0]);

    }

    @Test
    public void getReservationById() {
        Long id = 10L;
        Optional<Reservation> found = reservationService.getReservationById(id);
        assertEquals(found.get().getId(),id);
    }

    @Test
    public void getAllReservation() {
        Reservation test = new Reservation();
        test.setId(10L);

        List<Reservation> all = new ArrayList<>();
        all.add(test);

        List <Reservation> allReservation = reservationService.getAllReservation();
        assertEquals(all, allReservation);
    }

    @Test
    public void save() {
        Reservation created = new Reservation();
        created.setId(20L);
        Reservation saved = reservationRepository.save(created);
        assertEquals(created.getId() , saved.getId());
    }
}