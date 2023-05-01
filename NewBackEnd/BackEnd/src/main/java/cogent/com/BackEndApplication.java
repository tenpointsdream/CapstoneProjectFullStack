package cogent.com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cogent.com.dto.QuestionDTO;
import cogent.com.entity.Answer;
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
		userService.addUser(new User("Khoa Ho", "user1", "password", "user1@gmail.com", UserType.ADMIN));
		userService.addUser(new User("Prajesh Sharma", "user2", "password", "user2@gmail.com", UserType.USER));
		userService.addUser(new User("Abdulkerim Mohammed", "user3", "password", "user3@gmail.com", UserType.USER));
		QuestionService questionService = ctx.getBean(QuestionService.class);
		List<User> users = userService.getAllUsers();
		questionService.addQuestion(new QuestionDTO(0,"Test description", "test.jpg", "04-26-2023, 12:36", false,
				"JavaScript", "Getting to know", null, users.get(1), null));
		questionService.addQuestion(new QuestionDTO(1, "Test description", "test.jpg", "04-26-2023, 12:36", true, "Angular",
				"What is Angular?", new ArrayList<Answer>(List.of(new Answer())), users.get(1), users.get(0)));
		questionService.addQuestion(new QuestionDTO(2,"Test description", "test.jpg", "04-26-2023, 12:36", false, "Java",
				"Too many problems with Java", null, users.get(1), null));
		questionService.addQuestion(new QuestionDTO(3,"Test description", "test.jpg", "04-26-2023, 12:36", false,
				"SpringBoot", "Can't run Maven Project", null, users.get(1), null));

		List<QuestionDTO> questions = questionService.getAllQuestion();
		//AnswerService answerService = ctx.getBean(AnswerService.class);
		/*
		 * answerService.addAnswer(new AnswerDTO("I don't really know",
		 * "smileyface.jpg", false, "04-30-2023, 23:14", questions.get(1), null,
		 * users.get(2))); answerService.addAnswer(new Answer("I doubt it",
		 * "smileyface.jpg", false, "04-30-2023, 23:14", questions.get(1), null,
		 * users.get(2))); answerService.addAnswer(new Answer("I know it",
		 * "smileyface.jpg", true, "04-30-2023, 23:14", questions.get(1), users.get(0),
		 * users.get(2))); List<Answer> answers = answerService.getAllAnswersFalse();
		 * questionService.addQuestion(new Question("Test Quetsion", "test.jpg",
		 * "04-26-2023, 12:36", true, "Angular", "What is in this question?", new
		 * ArrayList<Answer>(List.of(new Answer("I know it", "smileyface.jpg", true,
		 * "04-30-2023, 23:14", questions.get(1), users.get(0), users.get(2)))),
		 * users.get(1), users.get(0))); questions = questionService.getAllQuestion();
		 */
		System.out.println(questions.toArray().length);
//		System.out.println(answers.get(0).getId());
//		System.out.println(answers.get(0).getDescription_answer());
//		System.out.println(answers.get(0).getDatetime());
//		System.out.println(answers.get(0).getStatus());
		System.out.println(questions.toString());
	}

}
