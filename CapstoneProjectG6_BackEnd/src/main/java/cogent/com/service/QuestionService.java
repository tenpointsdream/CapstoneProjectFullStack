package cogent.com.service;

import cogent.com.entity.Question;

import java.util.List;

public interface QuestionService {
	public void addQuestion(Question question);

	public void updateQuestion(Question question);

	public void deleteQuestionById(int questionId);

	public List<Question> getAllQuestions();

	public List<Question> getAllQuestionsFalse();

	public List<Question> getQuestionsByTopic(String topic);

	public Question getQuestionById(int questionId);
}