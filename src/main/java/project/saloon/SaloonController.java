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



@RestController
@RequestMapping("/saloon")
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
                "Portugal", "Aberto", "Cabeleireiro", "235463928",
                "Desde cortes extravagantes, a simples retoques, aqui encontrará o melhor atendimento e profissionalismo em Portugal.", "https://fotos.vivadecora.com.br/decoracao-salao-de-beleza-piso-de-porcelanato-claro-e-cadeira-de-couro-preta-revistavd-187516-square_cover_xsmall.jpg", "Rua D.José, 3100-450", user);
        Service service = new Service();
       
        service.setName("Corte de cabelo");
        service.setPrice(9.99);
        service.setAvailable("Disponível");
        service.setDescription("Feminino e Masculino");
        saloonRepository.save(saloon);
        service.setSaloon(saloon);
    
        serviceRepository.save(service);

        Service service_2 = new Service();
       
        service_2.setName("Pintura");
        service_2.setPrice(40.99);
        service_2.setAvailable("Disponível");
        service_2.setDescription("Feminino e Masculino");
        service_2.setSaloon(saloon);
        serviceRepository.save(service_2);

        Saloon b=new Saloon("Barberhood","3100-480","Lisboa","Portugal","Aberto","Barbeiro","951882438","Os serviços de Barberhood o distinguem para os cortes clássicos executados com domínio extremo e precisão nos detalhes. Para além de oferecer uma qualidade excelente nos tratamentos, eles oferecem também bebida e cervejas a todos os clientes.","https://barberhood.pt/wp-content/uploads/2019/11/cort.jpg","Av. Óscar Monteiro Torres 6A, 1000-219",user);
        saloonRepository.save(b);

        Service service_3 = new Service();
       
        service_3.setName("Barba - navalha");
        service_3.setPrice(8.99);
        service_3.setAvailable("Disponível");
        service_3.setDescription("Masculino");
        service_3.setSaloon(b);
        serviceRepository.save(service_3);

        Service service_4 = new Service();
       
        service_4.setName("Corte de cabelo/Rapar");
        service_4.setPrice(7.99);
        service_4.setAvailable("Disponivel");
        service_4.setDescription("Masculino");
        service_4.setSaloon(b);
        serviceRepository.save(service_4);

        Service service_5 = new Service();
       
        service_5.setName("Combo (barba e corte)");
        service_5.setPrice(15.99);
        service_5.setAvailable("Disponivel");
        service_5.setDescription("Masculino");
        service_5.setSaloon(b);
        serviceRepository.save(service_5);
   
        Schedule schedule1=new Schedule(new Time(12,0,0), new Time(13,0,0));
        schedule1.setService(service);
        scheduleRepository.save(schedule1);
        Schedule schedule2=new Schedule(new Time(14,0,0),new Time(15,0,0));
        schedule2.setService(service);
        scheduleRepository.save(schedule2);
        Schedule schedule3=new Schedule(new Time(12,0,0), new Time(16,0,0));
        schedule3.setService(service_2);
        scheduleRepository.save(schedule3);
        Schedule schedule4=new Schedule(new Time(17,0,0), new Time(18,0,0));
        schedule4.setService(service_3);
        scheduleRepository.save(schedule4);

      

        return "saved";
    }
}
