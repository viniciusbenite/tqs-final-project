package project.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.schedule.Schedule;
import project.reservation.Reservation;
import project.saloon.Saloon;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

// Service serves to list the services of the various saloons, one saloons has many services

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private String available;


    // Salão que oferece o serviço
    @ManyToOne
    @JoinColumn(name="saloon_id")
    private Saloon saloon;

    // Horario do serviço
    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<Schedule> schedules;


    // reservas do serviço
    @JsonIgnore
    @OneToMany(mappedBy = "services")
    private Set<Reservation> reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Saloon getSaloon() {
        return saloon;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Set<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }
}