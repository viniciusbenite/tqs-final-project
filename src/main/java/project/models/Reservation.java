package project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToOne
    @JoinColumn(name="users_id", nullable=false)
    private User users;
    @ManyToOne
    @JoinColumn(name="saloon_id", nullable=false)
    private Saloon saloon;

    private String date;





}
