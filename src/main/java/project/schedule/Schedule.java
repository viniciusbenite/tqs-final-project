package project.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.saloon.Saloon;
import project.service.Service;

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
    @JoinColumn(name = "service_id")
    private Service service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Saloon getSallon() {
        return sallon;
    }

    public void setSallon(Saloon sallon) {
        this.sallon = sallon;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
