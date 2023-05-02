package cogent.com.controller;

import java.util.List;

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

import cogent.com.dto.AnswerDTO;
import cogent.com.service.AnswerServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerServiceImpl answerService;

	@GetMapping("/getallanswers")
	public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
		List<AnswerDTO> answers = answerService.getAllAnswers();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PostMapping("/addanswer")
	public ResponseEntity<AnswerDTO> addAnwser(@RequestBody AnswerDTO answerDTO) {
		AnswerDTO addedAnswer = answerService.addAnswer(answerDTO);
		return new ResponseEntity<>(addedAnswer, HttpStatus.OK);
	}

	@GetMapping("/getanswerbyid/{id}")
	public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id);
		return answer == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@GetMapping("/getpendinganswers")
	public ResponseEntity<List<AnswerDTO>> getPendingAnswers() {
		List<AnswerDTO> answers = answerService.getAllAnswersFalse();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@GetMapping("/getanswersbyquestionid/{questionId}")
	public ResponseEntity<List<AnswerDTO>> getAnswersByQuestionId(@PathVariable int questionId) {
		List<AnswerDTO> answers = answerService.getAnswersByQuestionId(questionId);
		List<AnswerDTO> filteredAnswers = new ArrayList<AnswerDTO>();
		for (AnswerDTO answer : answers) {
			if (answer.isStatus() == true) {
				filteredAnswers.add(answer);
			}
		}
		return filteredAnswers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(filteredAnswers, HttpStatus.OK);
	}

	@PutMapping("/updateanswer/{id}")
	public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable("id") int id, @RequestBody AnswerDTO answerDTO) {
		AnswerDTO answer = answerService.getAnswerById(id);
		if (answer != null) {
			answer.setId(id);
			AnswerDTO updatedAnswer = answerService.updateAnswer(answer);
			return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswerbyid/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id);
		if (answer != null) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
