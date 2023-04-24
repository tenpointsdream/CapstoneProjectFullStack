package cogent.com.controller;

import cogent.com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
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

}
