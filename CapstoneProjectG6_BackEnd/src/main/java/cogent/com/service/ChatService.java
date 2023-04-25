package cogent.com.service;

import java.util.List;

import cogent.com.entity.Chat;

public interface ChatService {

    Chat addMessage(Chat chat);

    void deleteById(int id);

    List<Chat> getAllMessage();
}
