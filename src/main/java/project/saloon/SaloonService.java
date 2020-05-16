package project.saloon;

import project.user.User;

import java.util.List;

public interface SaloonService {

    User getSaloonByName(String name);
    List<Saloon> getAllSaloon();

    void save(Saloon saloon);
    void deleteAll();
    void deleteSaloonByName(String name);
}
