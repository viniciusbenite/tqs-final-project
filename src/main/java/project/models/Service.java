package project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @OneToMany(mappedBy="service")
    private Set<Schedule> schedules;


    // reservas do serviço
    @OneToMany(mappedBy="services")
    private Set<Reservation> reservation;



}