package project.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import project.service.Service;
import project.service.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAll() {
        Service service = new Service();
        Service service2 = new Service();
        List<Service> all = new ArrayList<Service>();

        testEntityManager.persistAndFlush(service);
        testEntityManager.persistAndFlush(service2);

        all.add(service);
        all.add(service2);

        testEntityManager.persistAndFlush(service);
        testEntityManager.persistAndFlush(service2);
        List<Service> found = serviceRepository.findAll();

        assertEquals(found , all);
    }

    @Test
    void getServiceByName() {
        Service service = new Service();
        service.setName("corte");

        testEntityManager.persistAndFlush(service);
        Service found = serviceRepository.getServiceByName(service.getName());

        assertEquals(found.getName() , service.getName());
    }

    @Test
    void getServiceById() {
        Service service = new Service();
        Long id = (Long) testEntityManager.persistAndGetId(service);

        Service found = serviceRepository.getServiceById(id).get();

        assertEquals(found.getId() , id);
    }

    @Test
    void deleteAll() {
        Service test = new Service();
        Service test2 = new Service();
        List<Service> all = new ArrayList<>();
        all.add(test);
        all.add(test2);

        testEntityManager.persistAndFlush(test);
        testEntityManager.persistAndFlush(test2);
        List<Service> found = serviceRepository.findAll();

        assertEquals(found , all);

        serviceRepository.deleteAll();
        assertTrue(serviceRepository.findAll().isEmpty());
    }

    @Test
    void deleteByName() {
        Service test = new Service();
        testEntityManager.persistAndFlush(test);
        Service found = serviceRepository.getServiceByName(test.getName());

        assertEquals(test.getName() , found.getName() );

        serviceRepository.deleteByName(test.getName());
        assertNull(serviceRepository.getServiceByName(test.getName()));

    }
}