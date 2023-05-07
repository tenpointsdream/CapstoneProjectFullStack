package cogent.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findByTopic(String topic);
	List<Question> findByStatus(boolean status);
	@Query(value = "SELECT COUNT(*) FROM question WHERE qcreated_by =  ?1", nativeQuery = true)
	int countByQcreatedBy(String username);
}
