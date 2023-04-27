package cogent.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	List<Answer> findByStatus(String aFalse);

	List<Answer> findAllById(int id);

	List<Answer> findByQuestionId(int questionId);
}
