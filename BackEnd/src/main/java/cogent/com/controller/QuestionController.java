package cogent.com.controller;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;
import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;
import cogent.com.service.AnswerService;
import cogent.com.service.QuestionServiceImpl;
import cogent.com.util.AppUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionServiceImpl questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@PostMapping(value = "/addquestion", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<String> addQuestion(@RequestParam("topic") String topic, @RequestParam("title") String title,
			@RequestParam("desc") String desc, @RequestParam("createdBy") String createdBy,
			@RequestPart("file") MultipartFile file) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setTopic(topic);
		questionDTO.setTitle(title);
		questionDTO.setDescriptionQuestion(desc);
		questionDTO.setStatus(false);
		questionDTO.setQcreated_by(createdBy);
		questionDTO.setDatetime(LocalDateTime.now().toString());
		questionDTO.setImageSrc(file.getOriginalFilename());
		questionService.addQuestion(questionDTO);
		return AppUtil.uploadFile(file, "question_images") ?
				new ResponseEntity<>("file not uploaded", HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>("file uploaded successfully", HttpStatus.CREATED);
	}

	@PostMapping("/add")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
	}

	@PutMapping("/updatequestionbyid/{id}")
	public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable("id") int id,
			@RequestBody QuestionDTO questionDTO) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionDTO.setId(id);
			QuestionDTO updatedQuestion = questionService.updateQuestion(questionDTO);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/addanswertoquestion/{question_id}")
	public ResponseEntity<QuestionDTO> addAnswerToQuestion(@PathVariable("question_id") int question_id,
			@RequestBody AnswerDTO answerDTO) {
		QuestionDTO updatedQuestion = questionService.addAnswerToQuestion(question_id, answerDTO);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}

	@GetMapping("/getproperties/{id}")
	public ResponseEntity<QuestionDTO> getProperties(@PathVariable("id") int id) {
		Question question = questionRepository.findById(id).orElse(new Question());
		QuestionDTO questionDTO = new QuestionDTO();
		BeanUtils.copyProperties(question, questionDTO);
		questionDTO.setAnswers(question.getAnswers());
		return ResponseEntity.ok(questionDTO);
	}

	@DeleteMapping("/deletequestionbyid/{id}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable("id") int id) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		if (optionalQuestion.isPresent()) {
			questionService.deleteQuestionById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getallquestions")
	public ResponseEntity<List<QuestionDTO>> getAllQuestion() {
		List<QuestionDTO> questions = questionService.getAllQuestion();
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbytopic/{topic}")
	public ResponseEntity<List<QuestionDTO>> getQuestionByTopic(@PathVariable("topic") String topic) {
		List<QuestionDTO> questions = questionService.getQuestionByTopic(topic);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getquestionbyid/{id}")
	public ResponseEntity<QuestionDTO> getAllQuestion(@PathVariable("id") int id) {
		Optional<QuestionDTO> optionalQuestion = questionService.getQuestionById(id);
		return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/getquestionbystatus/{status}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByStatus(@PathVariable("status") boolean status) {
		List<QuestionDTO> questions = questionService.getAllQuestionsByStatus(status);
		return questions == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(questions, HttpStatus.OK);
	}
}
