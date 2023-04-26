package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		User newUser = userService.addNewUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent())
			return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
		if (users != null)
			return new ResponseEntity<>(users, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getbytype/{userType}")
	public ResponseEntity<List<User>> getUsersByTypes(@PathVariable("userType") UserType userType) {
		List<User> users = userService.getUsersByType(userType);
		if (users != null)
			return new ResponseEntity<>(users, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
