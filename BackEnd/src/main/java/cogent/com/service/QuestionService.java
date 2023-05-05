package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;

public interface QuestionService {
	QuestionDTO addQuestion(QuestionDTO questionDTO);
	QuestionDTO updateQuestion(QuestionDTO questionDTO);
	void deleteQuestionById(int id);
	List<QuestionDTO> getAllQuestion();
	List<QuestionDTO> getQuestionByTopic(String topic);
	List<QuestionDTO> getAllQuestionsByStatus(boolean status);
	Optional<QuestionDTO> getQuestionById(int id);
	List<QuestionDTO> getQuestionByTitle(String title);
	QuestionDTO addAnswerToQuestion(int question_id, AnswerDTO answerDTO);
}
