package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cogent.com.entity.User;
import cogent.com.service.UserService;
import cogent.com.util.UserType;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		return userService.getUserById(id).map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Validated @RequestBody User user) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getbyname/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
		List<User> users = userService.getUsersByName(name);
		return users != null ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getbytype/{userType}")
	public ResponseEntity<List<User>> getUsersByTypes(@PathVariable("userType") UserType userType) {
		List<User> users = userService.getUsersByType(userType);
		return users != null ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
