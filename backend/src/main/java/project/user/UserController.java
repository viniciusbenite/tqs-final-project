package project.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.saloon.Saloon;
import project.user.User;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> all(){
        User user=new User("Cliente","cliente@gmail.com","ola","cliente",911882437);
        userService.save(user);
        User userDono=new User("Dono","dono@gmail.com","ola","dono",911882437);
        userService.save(userDono);
        return userService.getAllUser();
    }


 




}
