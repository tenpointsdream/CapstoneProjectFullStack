package cogent.com.controller;

import cogent.com.dto.AnswerDTO;
import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;
import cogent.com.service.AnswerServiceImpl;
import cogent.com.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerServiceImpl answerService;

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/getallanswers")
	public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
		List<AnswerDTO> answers = answerService.getAllAnswers();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PostMapping(value = "/addanswer", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> addAnswer(@RequestParam("question_id") String question_id,
			@RequestParam("desc") String desc, @RequestParam("createdBy") String createdBy,
			@RequestPart("file") MultipartFile file) {
		AnswerDTO answerDTO = new AnswerDTO();
		Question question = questionRepository.findById(Integer.parseInt(question_id)).orElse(new Question());
		answerDTO.setQuestion(question);
		answerDTO.setDescription_answer(desc);
		answerDTO.setStatus(false);
		answerDTO.setCreated_by(createdBy);
		answerDTO.setDatetime(LocalDateTime.now().toString());
		answerDTO.setImg_src(file.getOriginalFilename());
		answerService.addAnswer(answerDTO);
		return AppUtil.uploadFile(file, "answer_images") ?
				new ResponseEntity<>("file not uploaded", HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>("file uploaded successfully", HttpStatus.CREATED);
	}

	@GetMapping("/getanswerbyid/{id}")
	public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id).orElse(new AnswerDTO());
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@GetMapping("/getpendinganswers")
	public ResponseEntity<List<AnswerDTO>> getPendingAnswers() {
		List<AnswerDTO> answers = answerService.getAllAnswersFalse();
		return answers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@GetMapping("/getanswersbyquestionid/{questionId}")
	public ResponseEntity<List<AnswerDTO>> getAnswersByQuestionId(@PathVariable int questionId) {
		List<AnswerDTO> answers = answerService.getAllAnswersByQId(questionId);
		if (answers == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		answers.removeIf(answer -> !answer.isStatus());
		return new ResponseEntity<>(answers, HttpStatus.OK);
	}

	@PutMapping("/updateanswer/{id}")
	public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable("id") int id, @RequestBody AnswerDTO answerDTO) {
		Optional<AnswerDTO> answer = answerService.getAnswerById(id);
		if (answer.isPresent()) {
			answerDTO.setId(id);
			AnswerDTO updatedAnswer = answerService.updateAnswer(answerDTO);
			return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteanswerbyid/{id}")
	public ResponseEntity<?> deleteAnswerById(@PathVariable("id") int id) {
		answerService.getAnswerById(id); //.orElse(new AnswerDTO());
		answerService.deleteAnswerById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
