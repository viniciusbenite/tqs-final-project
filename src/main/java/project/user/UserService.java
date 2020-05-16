package project.user;

import java.util.List;

public interface UserService {

    User getUserByName(String name);
    List<User> getAllUser();

    void save(User user);
    void deleteAll();
    void deleteUserByName(String name);
}
