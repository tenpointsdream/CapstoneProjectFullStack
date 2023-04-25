package cogent.com.service;

import cogent.com.entity.Question;
import cogent.com.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    private QuestionRepository questionRepository;
  @Autowired

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void addQuestion(Question question) {
       questionRepository.save(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(int questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getAllQuestionsFalse() {
        return questionRepository.findByStatus("false");
    }

    @Override
    public List<Question> getQuestionsByTopic(String topic) {
        return questionRepository.finByTopic(topic);
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questionRepository.findById(questionId).get();
    }
}
