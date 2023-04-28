package cogent.com;

import cogent.com.entity.Question;
import cogent.com.service.QuestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cogent.com.entity.User;
import cogent.com.service.UserService;

import java.util.List;

import static cogent.com.util.UserType.*;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
		UserService userService = ctx.getBean(UserService.class);
		userService.addUser(new User("Timmy", "timmy", "timmy123", "timmy@gmail.com", USER));
		userService.addUser(new User("Gyanendra", "gyanendra", "gyanendra123", "gyanendra@gmail.com", ADMIN));

		QuestionService questionService = ctx.getBean(QuestionService.class);
		List<User> users = userService.getAllUsers();
		String munenyo = "https://i.kym-cdn.com/entries/icons/original/000/037/291/chucover.jpg";
		questionService.addQuestion(new Question("Test description", munenyo, "04-26-2023, 12:36",
				"false", "C", "segv", null, users.get(0), null));
	}

}
