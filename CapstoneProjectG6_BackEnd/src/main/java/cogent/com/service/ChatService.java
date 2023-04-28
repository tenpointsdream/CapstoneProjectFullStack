package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.Chat;

public interface ChatService {
	public Chat addNewChat(Chat chat);
	public void deleteChatById(int id);
	public List<Chat> getAllChat();
	public Optional<Chat> getChatById(int id);
}
