package cogent.com.controller;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;
import cogent.com.entity.Chat;
import cogent.com.entity.Email;
import cogent.com.entity.User;
import cogent.com.service.*;
import cogent.com.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cogent.com.util.AppUtil.sha256;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private UserService userService;

	@Autowired
	private QuestionServiceImpl questionService;

	@Autowired
	private AnswerServiceImpl answerService;

	@Autowired
	private ChatService chatService;

	@Autowired
	private EmailService emailService;

	// User Controllers
	@PostMapping("/user/adduser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userService.addNewUser(user);
		newUser.setPassword(sha256(user.getPassword()));
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
		return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/user/getallusers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// Question Controllers
	@PostMapping("/question/addquestion")
	public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO) {
		QuestionDTO addedQuestion = questionService.addQuestion(questionDTO);
		return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/question/updatequestion/{id}")
	public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable("id") int id,
			@RequestBody QuestionDTO questionDTO) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionDTO.setId(id);
			QuestionDTO updatedQuestion = questionService.updateQuestion(questionDTO);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/question/deletequestionbyid/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/question/getallquestions")
	public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
		List<QuestionDTO> questions = questionService.getAllQuestion();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("question/searchquestions/{topic}/{title}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByTitle(@PathVariable("topic") String topic,
			@PathVariable("title") String title) {
		List<QuestionDTO> questionsByTopic = questionService.getQuestionByTopic(topic);
		if (questionsByTopic == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		List<QuestionDTO> questionsByTitle = new ArrayList<>();
		for (QuestionDTO question : questionsByTopic)
			if (question.getTitle().toLowerCase().contains(title.toLowerCase()))
				questionsByTitle.add(question);

		List<QuestionDTO> questionFiltered = new ArrayList<>();
		for (QuestionDTO questionByTitle : questionsByTitle) {
			if (questionByTitle.isStatus() == true) {
				questionFiltered.add(questionByTitle);
			}
		}
		return new ResponseEntity<>(questionFiltered, HttpStatus.OK);
	}

	@GetMapping("/question/getquestionbytopic/{topic}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByTopic(@PathVariable("topic") String topic) {
		List<QuestionDTO> questions = questionService.getQuestionByTopic(topic);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/question/getquestionbyid/{id}")
	public ResponseEntity<QuestionDTO> getQuestionsById(@PathVariable("id") int id) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/question/getquestionbystatus/{status}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByStatus(@PathVariable("status") boolean status) {
		List<QuestionDTO> questions = questionService.getAllQuestionsByStatus(status);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}

	// Answer Controllers
	@PostMapping("/answer/addanswer")
	public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO) {
		AnswerDTO addedAnswer = answerService.addAnswer(answerDTO);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("answer/getanswerbyid/{id}")
	public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id).get();
		return answer == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@PutMapping("/answer/updateanswer/{id}")
	public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable("id") int id, @RequestBody AnswerDTO answerDTO) {
		AnswerDTO answer = answerService.getAnswerById(id).get();
		if (answer != null) {
			answerDTO.setId(id);
			AnswerDTO updatedAnswer = answerService.updateAnswer(answer);
			return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswerbyid/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id).get();
		if (answer != null) {
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
	public ResponseEntity<String> sendEmail(@RequestBody Email email) {
		return ResponseEntity.ok(emailService.sendEmail(email));
	}
}
