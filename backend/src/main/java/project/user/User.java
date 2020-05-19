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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data    //lombok notations
@NoArgsConstructor
@AllArgsConstructor
@Table(	name = "users")      // "user" is a reserved keyword for postgre so table name has to be "users"
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 120)
    private String password;

   
    private String type;
    private String name;
    private int phoneNumber;


    


    // reservas do cliente
    @JsonIgnore
    @OneToMany(mappedBy="users")
    private Set<Reservation> reservation;


    // saloes ao qual ele é o dono
    @JsonIgnore
    @OneToMany(mappedBy="owner")
    private Set<Saloon> saloons;



    public User(String name, String email, String password, String type, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User( String email, String password) {
        this.type = ("cliente");
        this.phoneNumber = 0;
        this.email = email;
        this.password = password;
    }

    
 




    /*// generate password
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
    */

}
