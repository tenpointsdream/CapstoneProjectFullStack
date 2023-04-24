package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer>{

}
