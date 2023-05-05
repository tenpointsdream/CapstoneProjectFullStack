package cogent.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.dto.AnswerDTO;
import cogent.com.entity.Answer;
import cogent.com.entity.Question;
import cogent.com.repository.AnswerRepository;
import cogent.com.repository.QuestionRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionServiceImpl questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<AnswerDTO> getAllAnswers() {
		List<AnswerDTO> answersDTO = new ArrayList<>();
		answerRepository.findAll().forEach(answer -> answersDTO.add(questionService.convertAnswerToAnswerDTO(answer)));
		return answersDTO;
	}

	@Override
	public AnswerDTO addAnswer(AnswerDTO answerDTO) {
		return questionService
				.convertAnswerToAnswerDTO(answerRepository.save(questionService.convertAnswerDTOToAnswer(answerDTO)));
	}

	@Override
	public AnswerDTO updateAnswer(AnswerDTO answerDTO) {
		return questionService
				.convertAnswerToAnswerDTO(answerRepository.save(questionService.convertAnswerDTOToAnswer(answerDTO)));
	}

	@Override
	public Optional<AnswerDTO> getAnswerById(int id) {
		return questionService.convertAnswerToAnswerDTO(answerRepository.findById(id));
	}

	@Override
	public void deleteAnswerById(int id) {
		answerRepository.deleteById(id);
	}

	@Override
	public List<AnswerDTO> getAllAnswersFalse() {
		List<AnswerDTO> answersDTO = new ArrayList<>();
		answerRepository.findByStatus(false).
				forEach(answer -> answersDTO.add(questionService.convertAnswerToAnswerDTO(answer)));
		return answersDTO;
	}

	@Override
	public List<AnswerDTO> getAnswersByQuestionId(int questionId) {
		List<AnswerDTO> answersDTO = new ArrayList<>();
		answerRepository.findByQuestionId(questionId).
				forEach(answer -> answersDTO.add(questionService.convertAnswerToAnswerDTO(answer)));
		return answersDTO;
	}

	@Override
	public List<AnswerDTO> getAllAnswersByQId(int questionId) {
		List<AnswerDTO> answerDTOs = new ArrayList<>();
		Question question = questionRepository.findById(questionId).orElse(new Question());
		List<Answer> answers = question.getAnswers();
		answers.forEach((answer) -> answerDTOs.add(questionService.convertAnswerToAnswerDTO(answer)));
		return answerDTOs;
	}

}
