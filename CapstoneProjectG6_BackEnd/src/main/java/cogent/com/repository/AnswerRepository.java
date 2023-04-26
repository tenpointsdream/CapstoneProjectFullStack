package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
