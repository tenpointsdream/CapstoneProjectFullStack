package cogent.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.Answer;
import cogent.com.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}

	@Override
	public Answer addAnswer(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public Answer updateAnswer(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public Optional<Answer> getAnswerById(int id) {
		return answerRepository.findById(id);
	}

	@Override
	public void deleteAnswerById(int id) {
		answerRepository.deleteById(id);
	}

	@Override
	public List<Answer> getAllAnswersFalse() {
		return answerRepository.findByStatus("False");
	}

	@Override
	public List<Answer> getAllAnswersById(int id) {
		return answerRepository.findAllById(id);
	}

	@Override
	public List<Answer> getAnswerByQuestionId(int questionId) {
		return answerRepository.findByQuestionId(questionId);
	}

}
