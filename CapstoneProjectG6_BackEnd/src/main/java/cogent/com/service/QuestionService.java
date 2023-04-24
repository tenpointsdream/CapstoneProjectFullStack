package cogent.com.service;

import cogent.com.entity.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);

    Question updateQuestion(Question question);

    void deleteQuestionById(int questionId);

    List<Question> getAllQuestions();

    List<Question> getAllQuestionsFalse();

    List<Question> getQuestionsByTopic(String topic);

    Question getQuestionById(int questionId);
}
