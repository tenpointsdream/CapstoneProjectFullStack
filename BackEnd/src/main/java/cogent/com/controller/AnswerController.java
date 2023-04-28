package cogent.com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import cogent.com.service.AnswerService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@GetMapping("/getallanswers")
	public ResponseEntity<List<Answer>> getAllAnswers() {
		List<Answer> answers = answerService.getAllAnswers();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PostMapping("/addanswer")
	public ResponseEntity<Answer> addAnwser(@RequestBody Answer answer) {
		Answer addedAnswer = answerService.addAnswer(answer);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("getanswerbyid/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		return optionalAnswer.map(answer -> new ResponseEntity<>(answer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/updateanswer/{id}")
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

}
