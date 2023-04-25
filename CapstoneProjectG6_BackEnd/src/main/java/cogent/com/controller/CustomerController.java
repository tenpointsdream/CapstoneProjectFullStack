package cogent.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.Answer;
import cogent.com.entity.AuthRequest;
import cogent.com.entity.Chat;
import cogent.com.entity.User;
import cogent.com.exception.InvalidCredentialsException;
import cogent.com.exception.UserDoesNotExistException;
import cogent.com.service.QuestionService;
import cogent.com.service.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;

	@GetMapping("/")
	public String Home() {
		return "Home";
	}

	@PostMapping("/user/adduser")
	public ResponseEntity<?> addUser(User user) {
		userService.addUser(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user/updateuser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user) throws UserDoesNotExistException {
		if (userService.getUserById(id) == null) {
			throw new UserDoesNotExistException("User does not exist in the our record");
		} else {
			User existingUser = userService.getUserById(id);
			existingUser.setName(user.getName());
			existingUser.setUsername(user.getUsername());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			existingUser.setUserType(user.getUserType());
			userService.updateUser(existingUser);
			return ResponseEntity.ok(existingUser);
		}

	}

	@GetMapping("user/getbyLogin")
	public ResponseEntity<?> getLogin(@RequestBody AuthRequest authRequest) throws InvalidCredentialsException {
		User user = userService.getUserByName(authRequest.getUsername());
		if (user == null || !user.getPassword().equals(authRequest.getPassword())) {
			throw new InvalidCredentialsException("Invalid username or password");
		} else
			return ResponseEntity.ok(user);
	}

	@GetMapping("user/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
		User user = userService.getUserByName(name);
		return ResponseEntity.ok(user);
	}

	@GetMapping("user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("user/getall")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) throws UserDoesNotExistException {
		if (userService.getUserById(id) == null) {
			throw new UserDoesNotExistException("User does not exist in the our record");
		} else {
			User user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		}
	}

	@GetMapping("question/getall")
	public ResponseEntity<?> getAllQuestions() {
		return ResponseEntity.ok(null);
	}

	@GetMapping("question/false")
	public ResponseEntity<?> getAllQuestionFalse() {
		return ResponseEntity.ok(null);
	}

	@GetMapping("question/{topic}")
	public ResponseEntity<?> getQuestionByTopic(@PathVariable String topic) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("question/{id}")
	public ResponseEntity<?> getQuestionById(int id) {
		return ResponseEntity.ok(null);
	}

	@PostMapping("answer/addanswer")
	public ResponseEntity<?> addAnwser(@RequestBody Answer answer) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("answer/{id}")
	public ResponseEntity<?> getAnswerById(@PathVariable("id") int id) {
		return ResponseEntity.ok(null);
	}

	@PutMapping("answer/update/{id}")
	public ResponseEntity<?> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("answer/question/{id}")
	public ResponseEntity<?> getAnswerByQuestionId(@PathVariable int id) {
		return ResponseEntity.ok(null);
	}

	@PostMapping("chat/addmsg")
	public ResponseEntity<?> addMsg(@RequestBody Chat chat) {
		return ResponseEntity.ok(null);
	}

	@DeleteMapping("chat/delete/{id}")
	public ResponseEntity<?> deleteChatById(@PathVariable int id) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("chat/getallmsg")
	public ResponseEntity<?> getAllMsg() {
		return ResponseEntity.ok(null);
	}
}
