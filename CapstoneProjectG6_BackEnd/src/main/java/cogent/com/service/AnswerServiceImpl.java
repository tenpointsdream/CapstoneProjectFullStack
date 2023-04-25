package cogent.com.service;

import cogent.com.entity.Answer;
import cogent.com.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerRepository answerRepository;

	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	@Override
	public void addAnswer(Answer answer) {
		answerRepository.save(answer);
	}

	@Override
	public void updateAnswer(Answer answer) {
		answerRepository.save(answer);
	}

	@Override
	public void deleteAnswerById(int answerId) {
		answerRepository.deleteById(answerId);
	}

	@Override
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}

	@Override
	public List<Answer> getAllAnswersFalse() {
		return answerRepository.findByStatus("false");
	}

	@Override
	public List<Answer> getAllAnswersByQuestionId(int questionId) {
		return answerRepository.getAllQuestionById(questionId);
	}

	@Override
	public List<Answer> getAllAnswersById(int id) {
		return null;
	}

	@Override
	public Answer getAnswerById(int id) {
		return answerRepository.findById(id).get();
	}

}
