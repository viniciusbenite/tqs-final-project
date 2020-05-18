package project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameContainsIgnoreCase(String name);

    List<User> findAll();

    Optional<User> getUserById(Long id);

    void deleteAll();
    void deleteByName(String name);

}