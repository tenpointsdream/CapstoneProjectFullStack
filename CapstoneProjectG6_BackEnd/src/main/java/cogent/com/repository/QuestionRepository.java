package cogent.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    List<Question> findByTopic(String Topic);
    List<Question> findByStatus(String aFalse);
}
