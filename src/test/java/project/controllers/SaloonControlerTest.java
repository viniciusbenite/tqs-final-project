package project.controllers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import project.saloon.*;
import project.saloon.Saloon;
import project.saloon.SaloonController;
import project.saloon.SaloonRepository;
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
public class SaloonControlerTest {

    @Mock( lenient = true)
    SaloonRepository R;

    @Mock( lenient = true)
    SaloonService S;

    @Mock( lenient = true)
    private Model model;


    @InjectMocks
    SaloonController controller;


    @Test
    public void getAllSaloons() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        List<Saloon> allSaloons = singletonList(saloon);



        Mockito.when(S.getAllSaloon()).thenReturn(allSaloons);


        assertEquals(controller.all(),allSaloons);

    }


    @Test
    public void newSaloon() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        List<Saloon> allSaloons = singletonList(saloon);



        Mockito.when(R.save(saloon)).thenReturn(saloon);


        assertEquals(controller.newSaloon(saloon),saloon);

    }


    @Test
    public void getSaloon() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        List<Saloon> allSaloons = singletonList(saloon);


        Mockito.when(R.getSaloonById(saloon.getId())).thenReturn(java.util.Optional.of(saloon));


        assertEquals(controller.getSaloon(saloon.getId()),saloon);

    }

    @Test
    public void editSaloon() throws Exception {

        User user = new User("Fulano de Tal", "fulanodetal@gmail.com", "somepass");
        Saloon saloon = new Saloon("Saloon name", "7890", "Aveiro",
                "Portugal", "open", "barbeiro", "12345",
                "blabla", "someimage", "endereço", user);
        Service service = new Service();

        service.setId(1L);
        service.setPrice(9.99);
        service.setAvailable("yes");
        service.setDescription("Corte cabelo");
        Set<Service> services = new HashSet<>();
        services.add(service);

        saloon.setId(9L);
        saloon.setType("Some type of saloon");
        saloon.setName("Saloon One");
        saloon.setOwner(user);
        saloon.setServices(services);

        List<Saloon> allSaloons = singletonList(saloon);







        Mockito.when(R.getSaloonById(saloon.getId())).thenReturn(java.util.Optional.of(saloon));
        Mockito.when(R.save(saloon)).thenReturn(saloon);


        assertEquals(controller.editSaloon(saloon,saloon.getId()),saloon);

    }



}
