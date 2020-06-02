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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration()
public class ServiceServiceTest {

    @TestConfiguration
    static class ServiceServiceImplTestContextConfiguration {
        @Bean
        public ServiceService serviceService() {return new ServiceServiceImp(); }
    }

    @MockBean
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceService serviceService;

    @Before
    public void setUp() {
        Service test = new Service();
        test.setName("corte");
        test.setId(10L);
        Service test2 = new Service();
        test2.setName("corte_simples");

        List<Service> all = new ArrayList<Service>();
        all.add(test);
        all.add(test2);

        when(serviceRepository.getServiceByName(test.getName())).thenReturn(test);
        when(serviceRepository.getServiceByName(test2.getName())).thenReturn(test2);
        when(serviceRepository.findAll()).thenReturn(all);
        when(serviceRepository.save(Mockito.any(Service.class))).thenAnswer(i -> i.getArguments()[0]);

    }


    @Test
    public void getServiceByName() {
        String name = "corte";
        Service found = serviceService.getServiceByName(name);
        assertEquals(found.getName(),name);
    }

    @Test
    public void getAllService() {
        Service test = new Service();
        test.setName("corte");
        test.setId(10L);
        Service test2 = new Service();
        test2.setName("corte_simples");

        List<Service> all = new ArrayList<Service>();
        all.add(test);
        all.add(test2);

        List <Service> serviceAllUser = serviceService.getAllService();
        assertEquals(all, serviceAllUser);
    }

    @Test
    public void save() {
        Service created = new Service();
        created.setName("save");
        Service saved = serviceRepository.save(created);
        assertEquals(created.getName() , saved.getName());
    }
}