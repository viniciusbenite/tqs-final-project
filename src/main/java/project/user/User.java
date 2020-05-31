package project.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.reservation.Reservation;
import project.saloon.Saloon;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Entity
@Data    //lombok notations
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")      // "user" is a reserved keyword for postgre so table name has to be "users"
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String type;
   

    // reservas do cliente
    @JsonIgnore
    @OneToMany(mappedBy="users")
    private Set<Reservation> reservation;


    // saloes ao qual ele é o dono
    @JsonIgnore
    @OneToMany(mappedBy="owner")
    private Set<Saloon> saloons;



    public User(String name, String email, String password,String type) {
        this.name = name;
        this.email = email;
        this.password = get_SHA_512_SecurePassword(password, "1234");
        this.type=type;
    }




    // generate password
    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setTipo(String type) {
        this.type = type;
    }

    public Set<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(Set<Reservation> reservation) {
        this.reservation = reservation;
    }

    public Set<Saloon> getSaloons() {
        return saloons;
    }

    public void setSaloons(Set<Saloon> saloons) {
        this.saloons = saloons;
    }
}
