package project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

// Service serves to list the services of the various saloons, one saloons has many services

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "saloon")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Sallon_id", table = "saloon")
    private Saloon saloonId;
    private String name;
    private Double price;
    private String description;


}