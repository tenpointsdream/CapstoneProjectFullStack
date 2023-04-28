package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cogent.com.entity.Answer;
import cogent.com.service.AnswerService;

@RestController
@RequestMapping("/answer")
@CrossOrigin("http://localhost:4200/")
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@GetMapping("/getallanswers")
	public ResponseEntity<List<Answer>> getAllAnswers() {
		List<Answer> answers = answerService.getAllAnswers();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PostMapping("/addanswer")
	public ResponseEntity<Answer> addAnswer(Answer answer) {
		return new ResponseEntity<>(answerService.addAnswer(answer), HttpStatus.OK);
	}

	@GetMapping("getanswer/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		return answerService.getAnswerById(id).map(answer -> new ResponseEntity<>(answer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/updateanswer/{id}")
	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") int id, @RequestBody Answer answer) {
		if (answerService.getAnswerById(id).isPresent()) {
			answer.setId(id);
			return new ResponseEntity<>(answerService.updateAnswer(answer), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswer/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		if (answerService.getAnswerById(id).isPresent()) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
