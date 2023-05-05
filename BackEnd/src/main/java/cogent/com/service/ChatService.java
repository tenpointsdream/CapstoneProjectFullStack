package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.Chat;

public interface ChatService {
	Chat addNewChat(Chat chat);
	void deleteChatById(int id);
	List<Chat> getAllChat();
	Optional<Chat> getChatById(int id);
}
