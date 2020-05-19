package project.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.ServiceService;
import project.user.User;
import project.user.UserRepository;
import project.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/saloon")
public class SaloonController {

    @Autowired
    private SaloonService saloonService;
    @Autowired
    private UserService userService;



    @GetMapping("all")
    public List<Saloon> all(){
        return saloonService.getAllSaloon();
    }




    //-----------------------------------------------------------
    @GetMapping("teste")
    public String teste(){
        User a = new User("User" , "user@gmai.com" , "123","dono",984756253);
        Saloon b = new Saloon();
        userService.save(a);
        b.setOwner(a);
        saloonService.save(b);
        return "teste feito";
    }


}
