package cogent.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.dto.AnswerDTO;
import cogent.com.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionServiceImpl questionService;

	@Override
	public List<AnswerDTO> getAllAnswers() {
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		answerRepository.findAll().forEach(answer -> {
			answersDTO.add(questionService.convertAnswerToAnswerDTO(answer));
		});
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
	public AnswerDTO getAnswerById(int id) {
		return questionService.convertAnswerToAnswerDTO(answerRepository.findById(id).get());
	}

	@Override
	public void deleteAnswerById(int id) {
		answerRepository.deleteById(id);
	}

	@Override
	public List<AnswerDTO> getAllAnswersFalse() {
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		answerRepository.findByStatus(false).forEach(answer -> {
			answersDTO.add(questionService.convertAnswerToAnswerDTO(answer));
		});
		return answersDTO;
	}

	@Override
	public List<AnswerDTO> getAllAnswersById(int id) {
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		answerRepository.findAllById(id).forEach(answer -> {
			answersDTO.add(questionService.convertAnswerToAnswerDTO(answer));
		});
		return answersDTO;
	}

	@Override
	public List<AnswerDTO> getAnswersByQuestionId(int questionId) {
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		answerRepository.findByQuestionId(questionId).forEach(answer -> {
			answersDTO.add(questionService.convertAnswerToAnswerDTO(answer));
		});
		return answersDTO;
	}

}
