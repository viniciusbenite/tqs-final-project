package project.saloon;

import project.user.User;

import java.util.List;
import java.util.Optional;

public interface SaloonService {

    User getSaloonByName(String name);

    Optional<Saloon> getSaloonById(Long id);

    List<Saloon> getAllSaloon();

    void save(Saloon saloon);
    void deleteAll();
    void deleteSaloonByName(String name);
}
