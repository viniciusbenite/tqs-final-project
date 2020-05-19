package project.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserByName(String name);

    Optional<User> getUserById(Long id);

    List<User> getAllUser();

    void save(User user);
    void deleteAll();
    void deleteUserByName(String name);
}
