package cogent.com.service;

import cogent.com.entity.Answer;
import cogent.com.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService{
    private AnswerRepository answerRepository;
    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
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
    public void deleteAnswerById(int answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> getAllAnswersFalse() {
        return answerRepository.findAllAnswerFalse();
    }

    @Override
    public Answer getAnswerById(int answerId) {
        return answerRepository.findById(answerId).get();
    }

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        return answerRepository.findAnswersByQuestionId(questionId);
    }
}
