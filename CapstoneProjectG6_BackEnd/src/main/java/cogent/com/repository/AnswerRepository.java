package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{

    List<Answer> findByStatus(String aFalse);

    List<Answer> getAllQuestionById(int questionId);

}
