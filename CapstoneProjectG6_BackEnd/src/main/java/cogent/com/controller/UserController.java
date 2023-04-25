package cogent.com.controller;

import static cogent.com.util.UserType.USER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.User;
import cogent.com.exception.UserDoesNotExistException;
import cogent.com.service.UserService;

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
	public ResponseEntity<?> addUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/addnewuser")
	public ResponseEntity<?> addNewUser(@RequestBody User user) {
		User newUser = new User(user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), USER);
		userService.addNewUser(newUser);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user)
			throws UserDoesNotExistException {
		if (userService.getAllUsersById(id) == null) {
			throw new UserDoesNotExistException("User with ID [" + id + "] does not exist");
		} else {
			User existingUser = userService.getUserById(id);
			existingUser.setName(user.getName());
			existingUser.setUsername(user.getUsername());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			existingUser.setUserType(user.getUserType());
			userService.updateUser(existingUser);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
