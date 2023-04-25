package cogent.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.Question;
import cogent.com.exception.QuestionDoesNotExistException;
import cogent.com.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	@PostMapping("/addquestion")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		questionService.addQuestion(question);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getQuestionById(@PathVariable("id") int id) {
		Question question = questionService.getQuestionById(id);
		return new ResponseEntity<>(question, HttpStatus.OK);
	}

	@GetMapping("/allquestions")
	public ResponseEntity<?> getAllQuestion() {
		List<Question> questions = questionService.getAllQuestions();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@PutMapping("/updatequetion/{id}")
	public ResponseEntity<?> updateQuestion(@PathVariable("id") int id, Question question)
			throws QuestionDoesNotExistException {
		if (questionService.getQuestionById(id) == null) {
			throw new QuestionDoesNotExistException("Question with ID [" + id + "] does not exist");

		} else {
			Question existingQuestion = questionService.getQuestionById(id);
			existingQuestion.setAnswers(question.getAnswers());
			existingQuestion.setDatetime(question.getDatetime());
			existingQuestion.setDescription_question(question.getDescription_question());
			existingQuestion.setStatus(question.getStatus());
			existingQuestion.setTitle(question.getTitle());
			existingQuestion.setTopics(question.getTopics());
			existingQuestion.setImage_src(question.getImage_src());
			questionService.updateQuestion(existingQuestion);
			return new ResponseEntity<>(existingQuestion, HttpStatus.OK);
		}

	}

	@GetMapping("/getallquetsionFalse")
	public ResponseEntity<?> getAllQuestionFalse() {
		List<Question> questions = questionService.getAllQuestionsFalse();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("getquestionbyTopic/{topic}")
	public ResponseEntity<?> getQuestionByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionsByTopic(topic);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		questionService.deleteQuestionById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
