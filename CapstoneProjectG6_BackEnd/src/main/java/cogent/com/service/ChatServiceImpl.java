package cogent.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.Chat;
import cogent.com.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat addNewChat(Chat chat) {
		return chatRepository.save(chat);
	}

	@Override
	public void deleteChatById(int id) {
		chatRepository.deleteById(id);
	}

	@Override
	public List<Chat> getAllChat() {
		return chatRepository.findAll();
	}

	@Override
	public Optional<Chat> getChatById(int id) {
		return chatRepository.findById(id);
	}

}
