package cogent.com;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cogent.com.entity.Question;
import cogent.com.entity.User;
import cogent.com.service.QuestionService;
import cogent.com.service.UserService;
import cogent.com.util.UserType;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
		UserService userService = ctx.getBean(UserService.class);
		userService.addUser(new User("user1", "user1", "password", "user1@gmail.com", UserType.ADMIN));
		userService.addUser(new User("user2", "user2", "password", "user2@gmail.com", UserType.USER));

		QuestionService questionService = ctx.getBean(QuestionService.class);
		List<User> users = userService.getAllUsers();
		questionService.addQuetion(new Question("Test description", "test.jpg", "04-26-2023, 12:36", "Wrong", "Server",
				"Server", null, users.get(0), null));
	}

}
