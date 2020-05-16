package project.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.user.User;
import project.user.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/Saloon")
public class SaloonController {

    @Autowired
    private SaloonService saloonService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("all")
    public List<Saloon> all(){
        return saloonService.getAllSaloon();
    }




    //-----------------------------------------------------------
    @GetMapping("teste")
    public String teste(){
        User a = new User("User" , "user@gmai.com" , "123");
        Saloon b = new Saloon();
        userRepository.save(a);
        b.setOwner(a);
        saloonService.save(b);
        return "teste feito";
    }


}
