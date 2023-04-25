package cogent.com.service;

import cogent.com.entity.Answer;

import java.util.List;

public interface AnswerService {

	public void addAnswer(Answer answer);

	public void updateAnswer(Answer answer);

	public void deleteAnswerById(int answerId);

	List<Answer> getAllAnswers();

	List<Answer> getAllAnswersFalse();

	List<Answer> getAllAnswersByQuestionId(int questionId);

	List<Answer> getAllAnswersById(int id);

	Answer getAnswerById(int id);
}
