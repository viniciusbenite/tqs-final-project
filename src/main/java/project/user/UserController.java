package project.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static project.constants.Paths.USER;

@RestController
@RequestMapping(value = USER)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation("Get all users")
    @GetMapping
    public List<User> all() {
        return userService.getAllUser();
    }

    @ApiOperation("Create a new user")
    @PostMapping("/")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @ApiOperation("Get a single user")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @ApiOperation("Edit a user")
    @PutMapping("/{id}")
    public User editUser(@RequestBody User newUser,
                         @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setId(newUser.getId());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setTipo(newUser.getTipo());
                    user.setSaloons(newUser.getSaloons());
                    user.setReservation(newUser.getReservation());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @ApiOperation("Delete a user")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @ApiOperation("Delete all users")
    @DeleteMapping("/")
    public void purgeUser() {
        userRepository.deleteAll();
    }
}
