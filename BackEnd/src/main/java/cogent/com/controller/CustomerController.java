package cogent.com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import cogent.com.service.AnswerService;
import cogent.com.service.ChatService;
import cogent.com.service.QuestionService;
import cogent.com.service.UserService;
import cogent.com.util.UserType;

@CrossOrigin(origins = "http://localhost:4200/")
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

	// User Controllers
	@PostMapping("/user/adduser")
	public ResponseEntity<User> addUser(User user) {
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
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbyname/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
		List<User> users = userService.getUsersByName(name);
		if (users != null)
			return new ResponseEntity<>(users, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbytype/{userType}")
	public ResponseEntity<List<User>> getUsersByTypes(@PathVariable("userType") UserType userType) {
		List<User> users = userService.getUsersByType(userType);
		if (users != null)
			return new ResponseEntity<>(users, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		Optional<User> userOptional = userService.getUserById(id);
		if (userOptional.isPresent())
			return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Question Controllers
	@PostMapping("/question/addquestion")
	public ResponseEntity<Question> addQuestion(Question question) {
		Question addedQuestion = questionService.addQuetion(question);
		return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/question/updatequestion")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			question.setId(id);
			Question updatedQuestion = questionService.updateQuestion(question);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/question/deletequetionbyid/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/question/getallquestions")
	public ResponseEntity<List<Question>> getAllQuetion() {
		List<Question> questions = questionService.getAllQuestion();
		if (questions == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("questions/searchquestions/{topic}/{title}")
	public ResponseEntity<List<Question>> getQuestionsByTitle(@PathVariable("topic") String topic,
			@PathVariable("title") String title) {
		List<Question> questionsByTopic = questionService.getQuestionByTopic(topic);
		if (questionsByTopic == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else {
			List<Question> questionsByTitle = new ArrayList<Question>();
			for (Question question : questionsByTopic) {
				if (question.getTitle().contains(title))
					questionsByTitle.add(question);
			}
			return new ResponseEntity<>(questionsByTitle, HttpStatus.OK);
		}
	}

	@GetMapping("/question/getquestionbytopic/{topic}")
	public ResponseEntity<List<Question>> getQuetionByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionByTopic(topic);
		if (questions == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/question/getquestionbyid/{id}")
	public ResponseEntity<Question> getAllQuetion(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Answer Controllers
	@PostMapping("/answer/addanswer")
	public ResponseEntity<Answer> addAnwser(Answer answer) {
		Answer addedAnswer = answerService.addAnswer(answer);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("answer/getanswerbyid/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent())
			return new ResponseEntity<>(optionalAnswer.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/answer/updateanswer/{id}")
	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent()) {
			answer.setId(id);
			Answer updatedAnswer = answerService.updateAnswer(answer);
			return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswerbyid/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent()) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Chat Controllers
	@PostMapping("/chat/addmsg")
	public ResponseEntity<Chat> addMsg(@RequestBody Chat chat) {
		Chat addedChat = chatService.addNewChat(chat);
		return new ResponseEntity<>(addedChat, HttpStatus.CREATED);
	}

	@DeleteMapping("chat/deletechatbyid")
	public ResponseEntity<?> deleteChatById(@PathVariable("id") int id) {
		Optional<Chat> optionalChat = chatService.getChatById(id);
		if (optionalChat.isPresent()) {
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
