package project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.saloon.Saloon;

import javax.persistence.*;
import java.sql.Time;

// Como o Horario tem apenas um tempo inicial e um tempo final, este é usado tanto para marcar o tempo de um serviço e
// o horario de um salão



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Time startTime;
    private Time endTime;

    // Horario de um salão  (Um salão tem um horario (tempo em que abre e fecha))
    @OneToOne(mappedBy = "schedule")
    private Saloon sallon;

    // horario de um serviço    (um serviço vai ter varios horarios que os clientes vão poder escolher)
    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;
}
