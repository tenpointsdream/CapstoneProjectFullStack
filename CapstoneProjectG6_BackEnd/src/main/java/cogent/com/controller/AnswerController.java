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

import cogent.com.entity.Answer;
import cogent.com.service.AnswerService;

@RestController
@RequestMapping("answer")
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@GetMapping("/getallanswers")
	public ResponseEntity<List<Answer>> getAllAnswers() {
		List<Answer> answers = answerService.getAllAnswers();
		if (answers == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PostMapping("/addanswer")
	public ResponseEntity<Answer> addAnwser(Answer answer) {
		Answer addedAnswer = answerService.addAnswer(answer);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("getanswerbyid/{id}")
	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") int id) {
		Optional<Answer> optionalAnswer = answerService.getAnswerById(id);
		if (optionalAnswer.isPresent())
			return new ResponseEntity<>(optionalAnswer.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateanswer/{id}")
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

}
