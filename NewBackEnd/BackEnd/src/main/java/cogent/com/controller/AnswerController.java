package cogent.com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cogent.com.dto.AnswerDTO;
import cogent.com.service.AnswerServiceImpl;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(value = "/addanswer", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> addAnswer(@RequestParam("desc") String desc,
											   @RequestParam("createdBy") String createdBy,
											   @RequestPart("file") MultipartFile file) {
		AnswerDTO answerDTO = new AnswerDTO();
		answerDTO.setDescription_answer(desc);
		answerDTO.setStatus(false);
		answerDTO.setCreated_by(createdBy);
		answerDTO.setDatetime(LocalDateTime.now().toString());
		answerDTO.setImg_src(file.getOriginalFilename());
		answerService.addAnswer(answerDTO);
		Path filepath = Paths.get("answer_files", file.getOriginalFilename());
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
		List<AnswerDTO> filteredAnswers = new ArrayList<>();
		for (AnswerDTO answer : answers)
			if (answer.isStatus())
				filteredAnswers.add(answer);
		return new ResponseEntity<>(filteredAnswers, HttpStatus.OK);
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
