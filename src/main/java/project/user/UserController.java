package project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public List<User> all(){
        return userService.getAllUser();
    }


    @GetMapping("teste")
    public String teste(){
        User a = new User("User" , "user@gmai.com" , "123");
        userService.save(a);
        return "teste feito";
    }


}
