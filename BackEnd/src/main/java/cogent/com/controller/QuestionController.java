package cogent.com.controller;

import java.util.List;
import java.util.Optional;

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
import cogent.com.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/addquestion")
	public ResponseEntity<Question> addQuestion(Question question) {
		Question addedQuestion = questionService.addQuetion(question);
		return new ResponseEntity<>(addedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/updatequestion")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			question.setId(id);
			Question updatedQuestion = questionService.updateQuestion(question);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deletequetionbyid/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getallquestions")
	public ResponseEntity<List<Question>> getAllQuetion() {
		List<Question> questions = questionService.getAllQuestion();
		if (questions == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbytopic/{topic}")
	public ResponseEntity<List<Question>> getQuetionByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionByTopic(topic);
		if (questions == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbyid/{id}")
	public ResponseEntity<Question> getAllQuetion(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
