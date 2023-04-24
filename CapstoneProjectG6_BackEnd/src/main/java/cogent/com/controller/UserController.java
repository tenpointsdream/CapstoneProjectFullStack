package cogent.com.controller;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cogent.com.util.UserType.USER;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return "Home";
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/addnewuser")
	public String addNewUser(@RequestParam String name, @RequestParam String username, @RequestParam String email,
			@RequestParam String password) {
		User user = new User(name, username, password, email, USER); // create a new user
		userRepository.save(user); // save the user to the database using the user repository

		return "User added: " + name;
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
