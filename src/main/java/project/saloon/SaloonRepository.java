package project.saloon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;

@Repository
@Transactional
public interface SaloonRepository extends JpaRepository<Saloon, Long> {

    User findByNameContainsIgnoreCase(String name);
    List<Saloon> findAll();

    void deleteAll();
    void deleteByName(String name);

}