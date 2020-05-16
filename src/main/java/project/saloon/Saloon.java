package project.saloon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.models.Schedule;
import project.models.Service;
import project.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saloon")
public class Saloon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String postalCode;
    private String city;
    private String country;
    private String status;  // open or closed
    private String type;    // salão cabeleireiro / barbeiro / esteticista ...
    private String contact;

    // Horario do salão
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule")
    private Schedule schedule;


    // Dono do salão
    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;


    // Serviços do salão
    @JsonIgnore
    @OneToMany(mappedBy="saloon")
    private Set<Service> services;




    public void setOwner(User owner) {
        this.owner = owner;
    }
}
