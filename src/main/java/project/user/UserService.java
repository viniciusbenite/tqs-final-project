package project.user;

import java.util.List;

public interface UserService {

    User getUserByName(String name);
    List<User> getAllUser();
    User save(User city);

    void deleteAll();
    void deleteUserByName(String name);
}
