package project.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import project.saloon.Saloon;
import project.schedule.*;
import project.schedule.Schedule;
import project.schedule.ScheduleController;
import project.schedule.ScheduleRepository;
import project.schedule.Schedule;
import project.service.Service;
import project.user.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {


    @Mock( lenient = true)
    ScheduleRepository R;

    @Mock( lenient = true)
    ScheduleService S;

    @Mock( lenient = true)
    private Model model;


    @InjectMocks
    ScheduleController controller;


    @Test
    public void getAllSchedules() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Schedule> allSchedules = singletonList(schedule);



        Mockito.when(S.getAllSchedule()).thenReturn(allSchedules);


        assertEquals(controller.all(),allSchedules);

    }


    @Test
    public void newSchedule() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Schedule> allSchedule = singletonList(schedule);


        Mockito.when(R.save(schedule)).thenReturn(schedule);


        assertEquals(controller.newSchedule(schedule),schedule);

    }


    @Test
    public void getSchedule() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Schedule> allSchedule = singletonList(schedule);


        Mockito.when(R.getScheduleById(schedule.getId())).thenReturn(java.util.Optional.of(schedule));


        assertEquals(controller.getSchedule(schedule.getId()),schedule);

    }

    @Test
    public void editSchedule() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Schedule> allSchedule = singletonList(schedule);





        Mockito.when(R.getScheduleById(schedule.getId())).thenReturn(java.util.Optional.of(schedule));
        Mockito.when(R.save(schedule)).thenReturn(schedule);


        assertEquals(controller.editSchedule(schedule,schedule.getId()),schedule);

    }
    
    
}
