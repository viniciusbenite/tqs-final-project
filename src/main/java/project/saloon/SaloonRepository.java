package project.saloon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SaloonRepository extends JpaRepository<Saloon, Long> {

    User findByNameContainsIgnoreCase(String name);

    List<Saloon> findAll();

    Optional<Saloon> getSaloonById(Long id);

    void deleteAll();
    void deleteByName(String name);

}