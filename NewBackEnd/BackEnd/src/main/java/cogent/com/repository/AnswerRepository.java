package cogent.com.repository;

import cogent.com.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	List<Answer> findByStatus(boolean status);

	List<Answer> findAllById(int id);
}
