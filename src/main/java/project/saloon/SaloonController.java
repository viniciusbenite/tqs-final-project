package project.saloon;
import java.io.*; 
import java.util.*; 
import java.sql.Time;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import project.user.User;
import project.user.UserRepository;
import project.service.ServiceRepository;
import project.service.Service;
import project.schedule.*;
import project.reservation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static project.constants.Paths.SALOON;

@RestController
@RequestMapping(value = SALOON)
@Api(value = "Métodos para gerenciar salões")
public class SaloonController {

    @Autowired
    private SaloonService saloonService;
    @Autowired
    private SaloonRepository saloonRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @ApiOperation("Get all saloons")
    @GetMapping
    public List<Saloon> all() {

        return saloonService.getAllSaloon();
     
    }

    @ApiOperation("Create a new saloon")
    @PostMapping("/")
    public Saloon newSaloon(@RequestBody Saloon newSaloon) {
       
        return saloonRepository.save(newSaloon);
    }

    @ApiOperation("Get a single saloon")
    @GetMapping("/{id}")
    public Saloon getSaloon(@PathVariable Long id) {
        return saloonRepository.getSaloonById(id)
                .orElseThrow(() -> new SaloonNotFoundException(id));
    }

    @ApiOperation("Edit a saloon")
    @PutMapping("/{id}")
    public Saloon editSaloon(@RequestBody Saloon newSaloon,
                             @PathVariable Long id) {
        return saloonRepository.findById(id)
                .map(saloon -> {
                    saloon.setOwner(newSaloon.getOwner());
                    saloon.setServices(newSaloon.getServices());
                    saloon.setSchedule(newSaloon.getSchedule());
                    saloon.setName(newSaloon.getName());
                    saloon.setPostalCode(newSaloon.getPostalCode());
                    saloon.setCity(newSaloon.getCity());
                    saloon.setCountry(newSaloon.getCountry());
                    saloon.setStatus(newSaloon.getStatus());
                    saloon.setType(newSaloon.getType());
                    saloon.setContact(newSaloon.getContact());

                    return saloonRepository.save(saloon);
                })
                .orElseGet(() -> {
                    newSaloon.setId(id);
                    return saloonRepository.save(newSaloon);
                });
    }

    @ApiOperation("Delete a saloon")
    @DeleteMapping("/{id}")
    public void deleteSaloon(@PathVariable Long id) {
        saloonRepository.deleteById(id);
    }

    @ApiOperation("Delete all saloons")
    @DeleteMapping("/")
    public void purgeSaloon() {
        saloonRepository.deleteAll();
    }


    @ApiOperation("Save info for tests")
    @GetMapping("/save")
    public String save() {
       
        

        Set<Reservation> setR=new HashSet<Reservation>();

        
        User user = new User("Alina", "alina@gmail.com", "ola");
        userRepository.save(user);
        Saloon saloon = new Saloon("Beldade", "7890", "Aveiro",
                "Portugal", "Aberto", "Cabeleireiro", "12345",
                "O melhor cabeleireiro de Portugal! Venha visitar-no!", "https://fotos.vivadecora.com.br/decoracao-salao-de-beleza-piso-de-porcelanato-claro-e-cadeira-de-couro-preta-revistavd-187516-square_cover_xsmall.jpg", "Rua D.Jose", user);
        Service service = new Service();
       
        service.setName("Corte de cabelo");
        service.setPrice(9.99);
        service.setAvailable("Disponível");
        service.setDescription("Só para mulheres");
        saloonRepository.save(saloon);
        service.setSaloon(saloon);
    
        serviceRepository.save(service);

        Saloon b=new Saloon("Machos","3100-480","Aveiro","Portugal","Aberto","Barbeiro","951882438","O melhor barbeiro de Portugal e arredores!","https://blog.mensmarket.com.br/wp-content/uploads/2019/01/salao-cabeleireiro-masculino-600x300.png","Rua Don José",user);
        saloonRepository.save(b);
   
        Schedule schedule1=new Schedule(new Time(12,0,0), new Time(13,0,0));
        schedule1.setService(service);
        scheduleRepository.save(schedule1);
        Schedule schedule2=new Schedule(new Time(14,0,0),new Time(15,0,0));
        schedule2.setService(service);
        scheduleRepository.save(schedule2);

      

        return "saved";
    }
}

