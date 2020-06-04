package project.saloon;

import project.user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service

public interface SaloonService {

    Saloon getSaloonByName(String name);

    Optional<Saloon> getSaloonById(Long id);

    List<Saloon> getAllSaloon();

    void save(Saloon saloon);
    void deleteAll();
    void deleteSaloonByName(String name);
}
