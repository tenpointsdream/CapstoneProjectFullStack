package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.dto.AnswerDTO;

public interface AnswerService {
	List<AnswerDTO> getAllAnswers();
	List<AnswerDTO> getAllAnswersByQId(int questionID);
	AnswerDTO addAnswer(AnswerDTO answerDTO);
	AnswerDTO updateAnswer(AnswerDTO answerDTO);
	Optional<AnswerDTO> getAnswerById(int id);
	void deleteAnswerById(int id);
	List<AnswerDTO> getAllAnswersFalse();
}
