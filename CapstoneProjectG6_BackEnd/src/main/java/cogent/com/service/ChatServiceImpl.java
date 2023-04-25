package cogent.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.Chat;
import cogent.com.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat addMessage(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public void deleteById(int id) {
        chatRepository.deleteById(id);
    }

    @Override
    public List<Chat> getAllMessage() {
        return chatRepository.findAll();
    }

}