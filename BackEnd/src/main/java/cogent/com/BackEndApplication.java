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
//	@Autowired
//	private UserRepository repository;
//	@PostConstruct
//	public void initUsers() {
//		List<User> users = Stream.of(
//				new User("user1", "user1", "password", "user1@gmail.com", UserType.ADMIN),
//				new User("user2", "user2", "password", "user2@gmail.com", UserType.USER)
//		).collect(Collectors.toList());
//		repository.saveAll(users);
//	}
public static void main(String[] args) {
	ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
	UserService userService = ctx.getBean(UserService.class);
	User gyanendra = new User("Gyanendra", "gyanendra", "gyanendra123", "gyanendra@gmail.com", UserType.ADMIN);
	User timmy = new User("Timmy", "timmy", "timmy123", "timmy@gmail.com", UserType.USER);
	userService.addUser(gyanendra);
	userService.addUser(timmy);

	QuestionService questionService = ctx.getBean(QuestionService.class);
	questionService.addQuestion(new Question("Test description", "test.jpg", "04-26-2023, 12:36",
			false, "JavaScript", "Getting to know", null, timmy, gyanendra));
}

}
