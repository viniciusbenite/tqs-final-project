package project.service;

import project.user.User;

import java.util.List;

public interface ServiceService {

    Service getServiceByName(String name);
    List<Service> getAllService();

    void save(Service service);
    void deleteAll();
    void deleteServiceByName(String name);
}
