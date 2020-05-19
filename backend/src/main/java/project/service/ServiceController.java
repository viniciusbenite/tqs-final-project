package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.user.User;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("all")
    public List<Service> all(){
        return serviceService.getAllService();
    }


    @GetMapping("teste")
    public String teste(){
        Service a = new Service();
        serviceService.save(a);
        return "teste feito";
    }


}
