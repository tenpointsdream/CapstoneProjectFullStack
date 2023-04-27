package cogent.com.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cogent.com.entity.*;
import cogent.com.service.*;
import cogent.com.util.UserType;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200/")
public class CustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private ChatService chatService;

	// User Controllers
	@PostMapping("/user/adduser")
	public ResponseEntity<User> addUser(User user) {
		return new ResponseEntity<>(userService.addNewUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/user/updateuser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Validated @RequestBody User user) {
		if (userService.getUserById(id).isPresent()) {
			user.setId(id);
			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbyname/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
		List<User> users = userService.getUsersByName(name);
		return users != null ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbytype/{userType}")
	public ResponseEntity<List<User>> getUsersByTypes(@PathVariable("userType") UserType userType) {
		List<User> users = userService.getUsersByType(userType);
		return users != null ? new ResponseEntity<>(users, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		Optional<User> userOptional = userService.getUserById(id);
		return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/user/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	// Question Controllers
	@PostMapping("/question/addquestion")
	public ResponseEntity<Question> addQuestion(Question question) {
		return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
	}

	@PutMapping("/question/updatequestion")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		if (questionService.getQuestionById(id).isPresent()) {
			question.setId(id);
			return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/question/deletequetion/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		if (questionService.getQuestionById(id).isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/question/getallquestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		List<Question> questions = questionService.getAllQuestion();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("question/searchquestions/{topic}/{title}")
	public ResponseEntity<List<Question>> getQuestionsByTitle(@PathVariable("topic") String topic,
															  @PathVariable("title") String title) {
		List<Question> questionsByTopic = questionService.getQuestionByTopic(topic);
		if (questionsByTopic == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else {
			List<Question> questionsByTitle = new ArrayList<>();
			for (Question question : questionsByTopic)
				if (question.getTitle().contains(title))
					questionsByTitle.add(question);
			return new ResponseEntity<>(questionsByTitle, HttpStatus.OK);
		}
	}

	@GetMapping("/question/getquestion/{topic}")
	public ResponseEntity<List<Question>> getQuestionByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionByTopic(topic);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/question/getquestion/{id}")
	public ResponseEntity<Question> getAllQuestion(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Answer Controllers
	@PostMapping("/answer/addanswer")
	public ResponseEntity<Answer> addAnswer(Answer answer) {
		return new ResponseEntity<>(answerService.addAnswer(answer), HttpStatus.OK);
	}

	@GetMapping("answer/getanswer/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		return optionalAnswer.map(answer -> new ResponseEntity<>(answer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/answer/updateanswer/{id}")
	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
		if (answerService.getAnswerById(id).isPresent()) {
			answer.setId(id);
			return new ResponseEntity<>(answerService.updateAnswer(answer), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("answer/deleteanswer/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		if (answerService.getAnswerById(id).isPresent()) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//Chat Controllers
	@PostMapping("/chat/addmsg")
	public ResponseEntity<Chat> addMsg(@RequestBody Chat chat) {
		return new ResponseEntity<>(chatService.addNewChat(chat), HttpStatus.CREATED);
	}

	@DeleteMapping("chat/deletechat/{id}")
	public ResponseEntity<?> deleteChatById(@PathVariable("id") int id) {
		if (chatService.getChatById(id).isPresent()) {
			chatService.deleteChatById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("chat/getallmsg")
	public ResponseEntity<List<Chat>> getAllMsg() {
		List<Chat> chats = chatService.getAllChat();
		if (chats == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(chats, HttpStatus.OK);
	}
}
