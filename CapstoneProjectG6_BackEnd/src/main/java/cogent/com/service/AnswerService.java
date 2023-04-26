package cogent.com.service;

import cogent.com.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

	public List<Answer> getAllAnswers();

	public Answer addAnswer(Answer answer);

	public Answer updateAnswer(Answer answer);

	public Optional<Answer> getAnswerById(int id);

	public void deleteAnswerById(int id);
	
	public List<Answer> getAllAnswersFalse();

	List<Answer> getAllAnswersById(int id);


}
