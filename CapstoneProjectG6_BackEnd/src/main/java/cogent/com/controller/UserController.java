package cogent.com.controller;

import cogent.com.entity.User;
import cogent.com.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        User user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/addnewuser")
    public String addNewUser(@RequestParam String name, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        User user = new User(name, username, password, email, UserType.USER); // create a new user
        userRepository.save(user); // save the user to the database using the user repository

        return "User added: " + name;
    }


}
