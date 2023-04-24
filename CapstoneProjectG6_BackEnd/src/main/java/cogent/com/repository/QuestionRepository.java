package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
