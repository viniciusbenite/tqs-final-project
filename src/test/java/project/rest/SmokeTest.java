package project.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import project.reservation.ReservationController;
import project.saloon.SaloonController;
import project.schedule.ScheduleController;
import project.service.ServiceController;
import project.user.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration()
public class SmokeTest {

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private SaloonController saloonController;

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private ServiceController serviceController;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(reservationController).isNotNull();
        assertThat(saloonController).isNotNull();
        assertThat(scheduleController).isNotNull();
        assertThat(serviceController).isNotNull();
        assertThat(userController).isNotNull();
    }
}