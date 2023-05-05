package cogent.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cogent.com.dto.AnswerDTO;
import cogent.com.dto.QuestionDTO;
import cogent.com.entity.Answer;
import cogent.com.entity.Chat;
import cogent.com.entity.User;
import cogent.com.service.AnswerService;
import cogent.com.service.ChatService;
import cogent.com.service.QuestionService;
import cogent.com.service.UserService;
import cogent.com.util.UserType;

@SpringBootApplication
public class BackEndApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
		UserService userService = ctx.getBean(UserService.class);
		userService.addUser(new User("Prajesh Sharma", "user1", "password", "user1@gmail.com", UserType.ADMIN));
		userService.addUser(new User("Khoa Ho", "user2", "password", "user2@gmail.com", UserType.USER));
		userService.addUser(new User("Abdulkerim Mohammed", "user3", "password", "user3@gmail.com", UserType.USER));
		QuestionService questionService = ctx.getBean(QuestionService.class);
		// List<User> users = userService.getAllUsers();
		questionService.addQuestion(new QuestionDTO(0, "Test description", "test.jpg", "04-26-2023, 12:36", false,
				"JavaScript", "Getting to know", null, "user2", null));
		questionService.addQuestion(new QuestionDTO(1, "Test description", "test.jpg", "04-26-2023, 12:36", true,
				"Angular", "What is Angular?", new ArrayList<>(Collections.singletonList(new Answer())), "user2", "user1"));
		questionService.addQuestion(new QuestionDTO(2, "Test description", "test.jpg", "04-26-2023, 12:36", false,
				"Java", "Too many problems with Java", null, "user2", null));
		questionService.addQuestion(new QuestionDTO(3, "Test description", "test.jpg", "04-26-2023, 12:36", false,
				"SpringBoot", "Can't run Maven Project", null, "user2", null));
		// QuestionServiceImpl questionSer = ctx.getBean(QuestionServiceImpl.class);
		// List<QuestionDTO> questions = questionService.getAllQuestion();
		AnswerService answerService = ctx.getBean(AnswerService.class);
		answerService.addAnswer(
				new AnswerDTO(1, "What?", "smileyface.jpg", false, "05-01-2023, 14:38", null, null, "user3"));
		answerService.addAnswer(
				new AnswerDTO(1, "I know this", "smileyface.jpg", true, "05-01-2023, 14:38", null, "user1", "user3"));
		answerService.addAnswer(
				new AnswerDTO(1, "I doubt it", "smileyface.jpg", true, "05-01-2023, 14:38", null, "user1", "user3"));
		answerService.addAnswer(
				new AnswerDTO(1, "Ha ha ha", "smileyface.jpg", false, "05-01-2023, 14:38", null, null, "user3"));

		ChatService chatService = ctx.getBean(ChatService.class);
		chatService.addNewChat(new Chat("user2", "user3", "Hello", "12/29/2023"));
		chatService.addNewChat(new Chat("user3", "user2", "How are you?", "12/29/2023"));
		chatService.addNewChat(new Chat("user4", "user2", "Say hi to me", "12/29/2023"));
		chatService.addNewChat(new Chat("user5", "user2", "Welcome", "12/29/2023"));
		chatService.addNewChat(new Chat("user2", "user4", "Do I know you?", "12/29/2023"));
		chatService.addNewChat(new Chat("user6", "user2", "I am the ADMIN", "12/29/2023"));
		// create directories for images
		for (String dir : new String[] { "question_images", "answer_images" }) {
			Path directory = Paths.get("frontend/src/assets/" + dir);
			if (Files.exists(directory))
				try (Stream<Path> pathStream = Files.walk(directory)) {
					pathStream.sorted(Comparator.reverseOrder()).forEach(path -> {
						try {
							Files.delete(path);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
				Files.createDirectories(directory);
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
	}

}
