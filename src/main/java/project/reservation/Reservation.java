package project.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.service.Service;
import project.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String date;  // dia do mês ( ex: 20/3/ - dia 20 do mes 3 (Março))
    private String time;  // horas da marcação ( 16:30:00 )


    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User users;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service services;


   public Reservation(String date,String time,User users,Service services) {
      this.date=date;
       this.time=time;
      this.users=users;
       this.services=services;
        }



    public void setUser(User user) {
        this.users = user;
    }

    public void setService(Service service) {
        this.services = service;
    }

    public User getUser() {
        return users;
    }

    public Service getService() {
        return services;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
