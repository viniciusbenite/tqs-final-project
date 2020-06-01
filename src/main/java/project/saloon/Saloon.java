package project.saloon;
import java.io.*; 
import java.util.*; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.schedule.Schedule;
import project.service.Service;
import project.user.User;
import java.util.Arrays; 
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.ArrayList; 

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
    private String type;    // salão cabeleireiro / barbeiro ...
    private String contact;
    private String description;
    private String image;
    private String address;

    // Horario do salão
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule")
    private Schedule schedule;


    // Dono do salão
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;


    // Serviços do salão
    
    @OneToMany(mappedBy = "saloon")
    @JsonIgnore
    private Set<Service> services;




    public Saloon(String name, String postalCode, String city,String country,String status, String type, String contact,String description,String image,String address,User owner) {
        this.name=name;
        this.postalCode=postalCode;
        this.city=city;
        this.country=country;
        this.status=status;
        this.type=type;
        this.contact=contact;
        this.description=description;
        this.address=address;
        this.owner=owner;
        this.image=image;
        
       
    }

    public Saloon(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<Service> getServices() {
        return services;
    
    }

    public void setServices(Set<Service> services) {
        
        this.services = services;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image=image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String address) {
        this.address=address;
    }

}
