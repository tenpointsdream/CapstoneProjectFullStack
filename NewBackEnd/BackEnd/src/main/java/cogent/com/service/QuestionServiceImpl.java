package cogent.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public void deleteQuestionById(int id) {
		questionRepository.deleteById(id);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}

	@Override
	public List<Question> getQuestionByTopic(String topic) {
		return questionRepository.findByTopic(topic);
	}

	@Override
	public Optional<Question> getQuestionById(int id) {
		return questionRepository.findById(id);
	}

	@Override
	public List<Question> getAllQuestionsByStatus(boolean status) {
		return questionRepository.findByStatus(status);
	}

	@Override
	public List<Question> getQuestionByTitle(String title) {
		List<Question> allQuestions = questionRepository.findAll();
		allQuestions.removeIf(str -> !str.getTitle().contains(title));
		return allQuestions;
	}

}
