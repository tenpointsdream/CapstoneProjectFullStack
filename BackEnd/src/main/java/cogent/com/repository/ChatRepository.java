package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> { }
