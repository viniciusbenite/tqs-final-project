package project.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import project.saloon.Saloon;
import project.saloon.SaloonRepository;
import project.saloon.SaloonService;
import project.saloon.SaloonServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SaloonServiceTest {

    @TestConfiguration
    static class SaloonServiceImplTestContextConfiguration {
        @Bean
        public SaloonService saloonService() { return new SaloonServiceImp(); }
    }
    @MockBean
    private SaloonRepository saloonRepository;

    @Autowired
    private SaloonService saloonService;

    @Before
    public void setUp() {
        Saloon test = new Saloon();
        test.setId(10L);
        test.setName("saloon");

        List<Saloon> all = new ArrayList<>();
        all.add(test);

        when(saloonRepository.findById(10L)).thenReturn(Optional.of(test));
        when(saloonRepository.findByNameContainsIgnoreCase(test.getName())).thenReturn(test);
        when(saloonRepository.findAll()).thenReturn(all);
        when(saloonRepository.save(Mockito.any(Saloon.class))).thenAnswer(i -> i.getArguments()[0]);

    }


    @Test
    public void getSaloonByName() {
        String name = "saloon";
        Saloon found = saloonService.getSaloonByName(name);
        assertEquals(found.getName(),name);
    }

    @Test
    public void getSaloonById() {
        Long id = 10L;
        Optional<Saloon> found = saloonService.getSaloonById(id);
        assertEquals(found.get().getId(),id);
    }

    @Test
    public void getAllSaloon() {
        Saloon test = new Saloon();
        test.setId(10L);
        test.setName("saloon");
        List<Saloon> all = new ArrayList<>();
        all.add(test);

        List <Saloon> allSchedule = saloonService.getAllSaloon();
        assertEquals(all, allSchedule);
    }

    @Test
    public void save() {
        Saloon created = new Saloon();
        Saloon saved = saloonRepository.save(created);

        assertEquals(created.getName() , saved.getName());
    }
}