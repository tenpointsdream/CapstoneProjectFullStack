package cogent.com.service;

import cogent.com.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer addAnswer(Answer answer);

    Answer updateAnswer(Answer answer);

    void deleteAnswerById(int answerId);

    List<Answer> getAllAnswers();

    List<Answer> getAllAnswersFalse();

    Answer getAnswerById(int answerId);

    List<Answer> getAnswersByQuestionId(int questionId);
}
