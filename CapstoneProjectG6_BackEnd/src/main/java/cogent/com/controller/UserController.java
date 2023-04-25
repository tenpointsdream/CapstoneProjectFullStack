package cogent.com.controller;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import cogent.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static cogent.com.util.UserType.USER;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userRepository.addUser(user);
    }

    @GetMapping("/addnewuser")
    public String addNewUser(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        User user = new User(name, username, password, email, USER);
        userRepository.save(user);
        return "User added: " + name;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") int id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId,
                                           @RequestBody User user) {
        return null;
    }

}
