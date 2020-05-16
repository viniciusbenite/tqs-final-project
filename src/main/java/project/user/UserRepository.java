package project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.user.User;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameContainsIgnoreCase(String name);
    List<User> findAll();

    void deleteAll();
    void deleteByName(String name);

}