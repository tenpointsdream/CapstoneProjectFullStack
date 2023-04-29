package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.Question;

public interface QuestionService {
	Question addQuestion(Question question);
	Question updateQuestion(Question question);
	void deleteQuestionById(int id);
	List<Question> getAllQuestion();
	List<Question> getQuestionByTopic(String topic);
	List<Question> getAllQuestionsByStatus(boolean status);
	Optional<Question> getQuestionById(int id);
	List<Question> getQuestionByTitle(String title);
}
