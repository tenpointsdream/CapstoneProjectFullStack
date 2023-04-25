package cogent.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cogent.com.entity.Answer;
import cogent.com.exception.AnswerDoesNotExistException;
import cogent.com.service.AnswerService;

@RestController
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@PostMapping("/addAnswer")
	public ResponseEntity<?> addAnswer(@Validated @RequestBody Answer answer) {
		answerService.addAnswer(answer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/updateAnswer")
	public ResponseEntity<?> updateAnswer(@PathVariable int id, @Validated @RequestBody Answer answer)
			throws AnswerDoesNotExistException {
		if (answerService.getAnswerById(id) == null) {
			throw new AnswerDoesNotExistException("Answer with ID [" + id + "] does not exist");
		} else {
			Answer existingAnswer = answerService.getAnswerById(id);
			existingAnswer.setQuestion(answer.getQuestion());
			existingAnswer.setStatus(answer.getStatus());
			existingAnswer.setDescription_answer(answer.getDescription_answer());
			existingAnswer.setDatetime(answer.getDatetime());
			answerService.updateAnswer(existingAnswer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteAnswerById/{id}")
	public ResponseEntity<?> deleteAnswerById(@Validated @PathVariable("id") int id) {
		answerService.deleteAnswerById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getAllAnswers")
	public ResponseEntity<?> getAllAnswers() {
		List<Answer> answers = answerService.getAllAnswers();
		return new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@GetMapping("/getAllAnswerFalse")
	public ResponseEntity<?> getAllAnswersFalse() {
		List<Answer> answers = answerService.getAllAnswersFalse();
		return new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@GetMapping("/getAllAnswerByQuestionId/{questionId}")
	public ResponseEntity<?> getAllAnswersByQuestionId(@Validated @PathVariable("questionId") int questionId) {
		List<Answer> answers = answerService.getAllAnswersByQuestionId(questionId);
		return new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@GetMapping("/getAllAnswerById/{answerId}")
	public ResponseEntity<?> getAllAnswersById(@Validated @PathVariable("answerId") int answerId) {
		List<Answer> answers = answerService.getAllAnswersById(answerId);
		return new ResponseEntity<>(answers, HttpStatus.OK);
	}
}
