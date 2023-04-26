package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.Answer;

public interface AnswerService {

	public List<Answer> getAllAnswers();

	public Answer addAnswer(Answer answer);

	public Answer updateAnswer(Answer answer);

	public Optional<Answer> getAnswerById(int id);

	public void deleteAnswerById(int id);
}
