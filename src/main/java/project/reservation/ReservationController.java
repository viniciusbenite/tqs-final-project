package project.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.Service;
import project.service.ServiceService;
import project.user.User;
import project.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServiceService serviceService;


    @GetMapping("all")
    public List<Reservation> all(){
        return reservationService.getAllReservation();
    }




    //-----------------------------------------------------------
    @GetMapping("teste")
    public String teste(){
        Reservation a = new Reservation();
        User b = new User("User" , "user@gmai.com" , "123");
        Service c = new Service();

        userService.save(b);
        serviceService.save(c);
        a.setUser(b);
        a.setService(c);

        reservationService.save(a);

        return "teste feito";
    }


}
