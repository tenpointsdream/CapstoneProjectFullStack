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
import cogent.com.entity.Chat;
import cogent.com.entity.Question;
import cogent.com.entity.User;
import cogent.com.exception.AnswerDoesNotExistException;
import cogent.com.exception.UserDoesNotExistException;
import cogent.com.service.AnswerService;
import cogent.com.service.ChatService;
import cogent.com.service.QuestionService;
import cogent.com.service.UserService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private ChatService chatService;

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
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user)
			throws UserDoesNotExistException {
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
	public ResponseEntity<?> getLogin(User user) {
		List<String> userCredential = userService.getLogin(user);
		return ResponseEntity.ok(userCredential);
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
		List<Question> questions = questionService.getAllQuestions();
		return ResponseEntity.ok(questions);
	}

	@GetMapping("question/false")
	public ResponseEntity<?> getAllQuestionFalse() {
		List<Question> questions = questionService.getAllQuestionsFalse();
		return ResponseEntity.ok(questions);
	}

	@GetMapping("question/{topic}")
	public ResponseEntity<?> getQuestionByTopic(@PathVariable String topic) {
		List<Question> questions = questionService.getQuestionsByTopic(topic);
		return ResponseEntity.ok(questions);
	}

	@GetMapping("question/{id}")
	public ResponseEntity<?> getQuestionById(int id) {
		Question question = questionService.getQuestionById(id);
		return ResponseEntity.ok(question);
	}

	@PostMapping("answer/addanswer")
	public ResponseEntity<?> addAnwser(@RequestBody Answer answer) {
		answerService.addAnswer(answer);
		return ResponseEntity.ok(answer);
	}

	@GetMapping("answer/{id}")
	public ResponseEntity<?> getAnswerById(@PathVariable("id") int id) {
		List<Answer> answers = answerService.getAllAnswersById(id);
		return ResponseEntity.ok(answers);
	}

	@PutMapping("answer/update/{id}")
	public ResponseEntity<?> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer)
			throws AnswerDoesNotExistException {
		if (answerService.getAnswerById(id) == null) {
			throw new AnswerDoesNotExistException("Answer with ID [" + id + "] does not exist");
		} else {
			Answer existingAnswer = answerService.getAnswerById(id);
			existingAnswer.setQuestion(answer.getQuestion());
			existingAnswer.setStatus(answer.getStatus());
			existingAnswer.setDescription_answer(answer.getDescription_answer());
			existingAnswer.setDatetime(answer.getDatetime());
			answerService.updateAnswer(existingAnswer);
			return ResponseEntity.ok(existingAnswer);
		}
	}

	@GetMapping("answer/question/{id}")
	public ResponseEntity<?> getAnswerByQuestionId(@PathVariable int id) {
		List<Answer> answers = answerService.getAllAnswersByQuestionId(id);
		return ResponseEntity.ok(answers);
	}

	@PostMapping("chat/addmsg")
	public ResponseEntity<?> addMsg(@RequestBody Chat chat) {
		chatService.addMessage(chat);
		return ResponseEntity.ok(chat);
	}

	@DeleteMapping("chat/delete/{id}")
	public ResponseEntity<?> deleteChatById(@PathVariable int id) {
		chatService.deleteById(id);
		return ResponseEntity.ok(id);
	}

	@GetMapping("chat/getallmsg")
	public ResponseEntity<?> getAllMsg() {
		List<Chat> chats = chatService.getAllMessage();
		return ResponseEntity.ok(chats);
	}
}
