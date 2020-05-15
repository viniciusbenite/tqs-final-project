package project.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import project.models.Reservation;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@Entity
@Data    //lombok notations
@NoArgsConstructor
@Table(name = "users")      // "user" is a reserved keyword for postgre so table name has to be "users"
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy="users")
    private Set<Reservation> reservation;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = get_SHA_512_SecurePassword(password, "1234");
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

}
