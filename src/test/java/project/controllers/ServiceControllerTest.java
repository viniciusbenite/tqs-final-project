package project.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import project.saloon.Saloon;
import project.service.*;
import project.service.Service;
import project.service.ServiceController;
import project.service.ServiceRepository;
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
public class ServiceControllerTest {


    @Mock( lenient = true)
    ServiceRepository R;

    @Mock( lenient = true)
    ServiceService S;

    @Mock( lenient = true)
    private Model model;


    @InjectMocks
    ServiceController controller;


    @Test
    public void getAllServices() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Service> allServices = singletonList(service);





        Mockito.when(S.getAllService()).thenReturn(allServices);


        assertEquals(controller.all(),allServices);

    }


    @Test
    public void newService() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Service> allService = singletonList(service);





        Mockito.when(R.save(service)).thenReturn(service);


        assertEquals(controller.newService(service),service);

    }


    @Test
    public void getService() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Service> allService = singletonList(service);



        Mockito.when(R.getServiceById(service.getId())).thenReturn(java.util.Optional.of(service));


        assertEquals(controller.getService(service.getId()),service);

    }

    @Test
    public void editService() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);

        Service service = new Service();
        Schedule schedule = new Schedule();

        List<Service> allService = singletonList(service);









        Mockito.when(R.getServiceById(service.getId())).thenReturn(java.util.Optional.of(service));
        Mockito.when(R.save(service)).thenReturn(service);


        assertEquals(controller.editService(service,service.getId()),service);

    }



}
