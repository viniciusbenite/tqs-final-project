package project.service;

import project.user.User;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    Service getServiceByName(String name);

    List<Service> getAllService();

    Optional<Service> getServiceById(Long id);

    void save(Service service);
    void deleteAll();
    void deleteServiceByName(String name);
}
