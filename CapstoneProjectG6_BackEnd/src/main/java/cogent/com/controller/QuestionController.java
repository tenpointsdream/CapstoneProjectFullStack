package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cogent.com.entity.Question;
import cogent.com.service.QuestionService;

@RestController
@RequestMapping("question")
@CrossOrigin("http://localhost:4200/")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@PostMapping("/addquestion")
	public ResponseEntity<Question> addQuestion(Question question) {
		return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
	}

	@PutMapping("/updatequestion")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		if (questionService.getQuestionById(id).isPresent()) {
			question.setId(id);
			return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deletequetion/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		if (questionService.getQuestionById(id).isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getallquestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		List<Question> questions = questionService.getAllQuestion();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbytopic/{topic}")
	public ResponseEntity<List<Question>> getQuestionByTopic(@PathVariable("topic") String topic) {
		List<Question> questions = questionService.getQuestionByTopic(topic);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbyid/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable("id") int id) {
		Optional<Question> optionalQuestion = questionService.getQuestionById(id);
		return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getquestionbystatus/{status}")
	public ResponseEntity<List<Question>> getQuestionsByStatus(@PathVariable("status") String status) {
		List<Question> questions = questionService.getAllQuestionsFalse();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(questions, HttpStatus.OK);
	}

}
