package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;

public interface QuestionService {

	public QuestionDTO addQuestion(QuestionDTO questionDTO);

	public QuestionDTO updateQuestion(QuestionDTO questionDTO);

	public void deleteQuestionById(int id);

	public List<QuestionDTO> getAllQuestion();

	public List<QuestionDTO> getQuestionByTopic(String topic);

	public List<QuestionDTO> getAllQuestionsByStatus(boolean status);

	public Optional<QuestionDTO> getQuestionById(int id);

	public List<QuestionDTO> getQuestionByTitle(String title);
	
	public QuestionDTO addAnswerToQuestion(int question_id, AnswerDTO answerDTO);
}
