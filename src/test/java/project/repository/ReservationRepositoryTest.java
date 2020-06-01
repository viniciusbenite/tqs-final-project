package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.reservation.Reservation;
import project.reservation.ReservationRepository;
import project.service.Service;
import project.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findAll() {
        Reservation test = new Reservation();
        Reservation test2 = new Reservation();
        Service service = new Service();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        test.setService(service);
        test2.setService(service);
        test.setUser(user);
        test2.setUser(user);
        testEntityManager.persistAndFlush(service);
        testEntityManager.persistAndFlush(user);

        List<Reservation> all = new ArrayList<Reservation>();

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);

        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test2);
        testEntityManager.persistAndFlush(test2);
        List<Reservation> found = reservationRepository.findAll();

        assertEquals(found , all);
    }

    @Test
    void deleteAll() {
        Reservation test = new Reservation();
        Reservation test2 = new Reservation();
        Service service = new Service();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        test.setService(service);
        test2.setService(service);
        test.setUser(user);
        test2.setUser(user);
        testEntityManager.persistAndFlush(service);
        testEntityManager.persistAndFlush(user);
        List<Reservation> all = new ArrayList<>();
        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Reservation> found = reservationRepository.findAll();

        assertEquals(found , all);

        reservationRepository.deleteAll();
        assertTrue(reservationRepository.findAll().isEmpty());
    }

    @Test
    void deleteById() {
        Reservation test = new Reservation();
        Service service = new Service();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        test.setService(service);
        test.setUser(user);
        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(service);
        test.setService(service);
        testEntityManager.persistAndFlush(test);
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Reservation found = reservationRepository.getReservationById(id).get();

        assertEquals(id , found.getId() );

        reservationRepository.deleteById(id);
        assertFalse(reservationRepository.getReservationById(id).isPresent());
    }

    @Test
    void getReservationById() {
        Reservation test = new Reservation();
        Service service = new Service();
        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        test.setUser(user);
        testEntityManager.persistAndFlush(user);
        testEntityManager.persistAndFlush(service);
        test.setService(service);
        testEntityManager.persistAndFlush(test);
        Long id = (Long) testEntityManager.persistAndGetId(test);

        Reservation found = reservationRepository.getReservationById(id).get();

        assertEquals(id , found.getId() );
    }
}