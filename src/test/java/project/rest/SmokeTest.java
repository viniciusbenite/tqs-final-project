package project.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import project.reservation.ReservationController;
import project.saloon.SaloonController;
import project.schedule.ScheduleController;
import project.service.ServiceController;
import project.user.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {ReservationController.class, ScheduleController.class, SaloonController.class,
        UserController.class, ServiceController.class})
public class SmokeTest {

    @Autowired
    @MockBean
    private ReservationController reservationController;

    @Autowired
    @MockBean
    private SaloonController saloonController;

    @Autowired
    @MockBean
    private ScheduleController scheduleController;

    @Autowired
    @MockBean
    private ServiceController serviceController;

    @Autowired
    @MockBean
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