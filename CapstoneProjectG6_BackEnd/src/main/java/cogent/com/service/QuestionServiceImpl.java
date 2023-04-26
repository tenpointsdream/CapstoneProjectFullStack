package cogent.com.service;

import java.util.*;

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
	public List<Question> getAllQuestionsFalse() {
		return questionRepository.findByStatus("False");
	}

	@Override
	public Optional<Question> getQuestionById(int id) {
		return questionRepository.findById(id);
	}

	@Override
	public List<Question> getQuestionByTitle(String title) {
		List<Question> allQuestions = questionRepository.findAll();
		List<Question> allQuestionsByTitle = new ArrayList<>();
		for (Question question : allQuestions)
			if (question.getTitle().contains(title))
				allQuestionsByTitle.add(question);
		return allQuestionsByTitle;
	}

}
