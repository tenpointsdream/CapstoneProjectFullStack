package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> finByTopic(String topic);

    List<Question> findByStatus(String aFalse);

    Question addQuestion(Question question);
}
