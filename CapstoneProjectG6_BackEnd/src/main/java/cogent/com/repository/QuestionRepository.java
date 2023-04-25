package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> finByTopic(String topic);

    List<Question> findAllByFlagFalse();

    Question addQuestion(Question question);
}
