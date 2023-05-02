package cogent.com.service;

import java.util.List;

import cogent.com.dto.AnswerDTO;

public interface AnswerService {

	public List<AnswerDTO> getAllAnswers();

	public List<AnswerDTO> getAllAnswersByQId(int questionID);

	public AnswerDTO addAnswer(AnswerDTO answerDTO);

	public AnswerDTO updateAnswer(AnswerDTO answerDTO);

	public AnswerDTO getAnswerById(int id);

	public void deleteAnswerById(int id);

	public List<AnswerDTO> getAllAnswersFalse();

	public List<AnswerDTO> getAllAnswersById(int id);

	public List<AnswerDTO> getAnswersByQuestionId(int questionId);
}
