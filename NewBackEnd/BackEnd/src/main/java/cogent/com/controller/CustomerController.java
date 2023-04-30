package cogent.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cogent.com.entity.*;
import cogent.com.service.*;
import cogent.com.util.UserType;

@CrossOrigin(origins = "*")
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

	@Autowired
	private EmailService emailService;

	// User Controllers
	@PostMapping("/user/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.addNewUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping("/user/updateuser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @Validated @RequestBody User user) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent()) {
			user.setId(id);
			User updatedUser = userService.updateUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
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
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Question Controllers
	@PostMapping("/question/addquestion")
	public ResponseEntity<Question> addQuestion(Question question) {
		Question addedQuestion = questionService.addQuestion(question);
		return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/question/updatequestion/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			question.setId(id);
			Question updatedQuestion = questionService.updateQuestion(question);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/question/deletequestionbyid/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/question/getallquestions")
	public ResponseEntity<List<Question>> geQuestionsById() {
		List<Question> questions = questionService.getAllQuestion();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("question/searchquestions/{topic}/{title}")
	public ResponseEntity<List<Question>> getQuestionsByTitle(@PathVariable("topic") String topic,
			@PathVariable("title") String title) {
		List<Question> questionsByTopic = questionService.getQuestionByTopic(topic);
		if (questionsByTopic == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		List<Question> questionsByTitle = new ArrayList<>();
		for (Question question : questionsByTopic)
			if (question.getTitle().toLowerCase().contains(title.toLowerCase()))
				questionsByTitle.add(question);
		return new ResponseEntity<>(questionsByTitle, HttpStatus.OK);
	}

	@GetMapping("/question/getquestionbytopic/{topic}")
	public ResponseEntity<List<Question>> getQuestionsByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionByTopic(topic);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/question/getquestionbyid/{id}")
	public ResponseEntity<Question> geQuestionsById(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Answer Controllers
	@PostMapping("/answer/addanswer")
	public ResponseEntity<Answer> addAnswer(Answer answer) {
		Answer addedAnswer = answerService.addAnswer(answer);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("answer/getanswerbyid/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		return optionalAnswer.map(answer -> new ResponseEntity<>(answer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/answer/updateanswer/{id}")
	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent()) {
			answer.setId(id);
			Answer updatedAnswer = answerService.updateAnswer(answer);
			return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswerbyid/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent()) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Chat Controllers
	@PostMapping("/chat/addmsg")
	public ResponseEntity<Chat> addMsg(@RequestBody Chat chat) {
		Chat addedChat = chatService.addNewChat(chat);
		return new ResponseEntity<>(addedChat, HttpStatus.CREATED);
	}

	@DeleteMapping("chat/deletechatbyid/{id}")
	public ResponseEntity<?> deleteChatById(@PathVariable("id") int id) {
		Optional<Chat> optionalChat = chatService.getChatById(id);
		if (optionalChat.isPresent()) {
			chatService.deleteChatById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("chat/getallmsg")
	public ResponseEntity<List<Chat>> getAllMsg() {
		List<Chat> chats = chatService.getAllChat();
		return chats == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(chats, HttpStatus.OK);
	}

	// Email Controller
	@PostMapping("/sendemail")
	public String sendEmail(@RequestBody Email email) {
		return emailService.sendEmail(email);
	}
}
