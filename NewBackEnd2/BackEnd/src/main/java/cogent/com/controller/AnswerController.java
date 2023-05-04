package cogent.com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cogent.com.dto.AnswerDTO;
import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;
import cogent.com.service.AnswerServiceImpl;

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
		Question question = questionRepository.findById(Integer.parseInt(question_id)).get();
		answerDTO.setQuestion(question);
		answerDTO.setDescription_answer(desc);
		answerDTO.setStatus(false);
		answerDTO.setCreated_by(createdBy);
		answerDTO.setDatetime(LocalDateTime.now().toString());
		answerDTO.setImg_src(file.getOriginalFilename());
		answerService.addAnswer(answerDTO);
		Path filepath = Paths.get("C:/CapstoneProjectFullStack/CapstoneProjectG6_FrontEnd/src/assets/answer_images", file.getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(filepath)) {
			os.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("file not uploaded", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("file uploaded successfully", HttpStatus.OK);
	}

	@GetMapping("/getanswerbyid/{id}")
	public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") int id) {
		AnswerDTO answer = answerService.getAnswerById(id).get();
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
		List<AnswerDTO> answers = answerService.getAllAnswersByQId(questionId);
		List<AnswerDTO> filteredAnswers = new ArrayList<AnswerDTO>();
		for (AnswerDTO answer : answers) {
			if (answer.isStatus()) {
				filteredAnswers.add(answer);
			}
		}
		return filteredAnswers == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(filteredAnswers, HttpStatus.OK);
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
		AnswerDTO answer = answerService.getAnswerById(id).get();
		if (answer != null) {
			answerService.deleteAnswerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
