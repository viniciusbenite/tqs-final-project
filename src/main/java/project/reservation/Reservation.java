package project.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.service.Service;
import project.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;  // dia do mês ( ex: 20/3/ - dia 20 do mes 3 (Março))
    private Time time;  // horas da marcação ( 16:30:00 )


    @ManyToOne
    @JoinColumn(name="users_id", nullable=false)
    private User users;

    @ManyToOne
    @JoinColumn(name="service_id", nullable=false)
    private Service services;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
