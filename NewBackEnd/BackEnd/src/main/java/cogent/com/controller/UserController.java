package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.AuthRequest;
import cogent.com.entity.User;
import cogent.com.service.UserService;
import cogent.com.util.JwtUtil;
import cogent.com.util.UserType;

import static cogent.com.util.AppUtil.sha256;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Great learning !!";
	}

//	@PostMapping("/authenticate")
//	public ResponseEntity<?> generateToken(@RequestBody AuthRequest request) {
//		authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//		return ResponseEntity.ok(jwtUtil.generateToken(request.getUsername()));
//	}
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		String encryptedPassword = sha256(authRequest.getPassword());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), encryptedPassword));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

//	@GetMapping("/login/{username}/{password}")
//	public ResponseEntity<Boolean> retrieveToken(@PathVariable("username") String username,
//			@PathVariable("password") String password) {
//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		String token = jwtUtil.generateToken(username);
//		return new ResponseEntity<>((token.length() > 0), HttpStatus.OK);
//	}

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.addNewUser(user);
		newUser.setPassword(sha256(newUser.getPassword()));
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		Optional<User> userOptional = userService.getUserById(id);
		return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/updateuserbyid/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Validated @RequestBody User user) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
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

	@GetMapping("/getbyusername/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		User user = null;
		if (userService.getUserByUsername(username).isPresent())
			user = userService.getUserByUsername(username).get();
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}
}
