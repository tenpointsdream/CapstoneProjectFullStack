package cogent.com;

//import cogent.com.dto.AnswerDTO;
//import cogent.com.dto.QuestionDTO;
//import cogent.com.entity.Answer;
//import cogent.com.entity.Chat;
//import cogent.com.entity.User;
//import cogent.com.service.AnswerService;
//import cogent.com.service.ChatService;
//import cogent.com.service.QuestionService;
//import cogent.com.service.UserService;
//import cogent.com.util.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.stream.Stream;

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
		@SuppressWarnings("unused")
		ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
//		UserService userService = ctx.getBean(UserService.class);
//		userService.addUser(new User("Khoa Ho", "user1", "password", "dangkhoa2207@hotmail.com", UserType.ADMIN));
//		userService.addUser(new User("Prajesh Sharma", "user2", "password", "user2@gmail.com", UserType.USER));
//		userService.addUser(new User("Abdulkerim Mohammed", "user3", "password", "user3@gmail.com", UserType.USER));
//		QuestionService questionService = ctx.getBean(QuestionService.class);
//		// List<User> users = userService.getAllUsers();
//		questionService.addQuestion(new QuestionDTO(0, "Test description", "javascript.jpg", LocalDateTime.now().toString(), false,
//				"JavaScript", "Getting to know", null, "user2", null));
//		questionService.addQuestion(new QuestionDTO(1, "Test description", "angular.jpg", LocalDateTime.now().toString(), true,
//
//				"Angular", "What is Angular?", new ArrayList<Answer>(Arrays.asList(new Answer())), "user2", "user1"));
//
//		questionService.addQuestion(new QuestionDTO(2, "Test description", "error.jpg", LocalDateTime.now().toString(), false,
//				"Java", "Too many problems with Java", null, "user2", null));
//		questionService.addQuestion(new QuestionDTO(3, "Test description", "springboot.jpg", LocalDateTime.now().toString(), false,
//				"SpringBoot", "Can't run Maven Project", null, "user2", null));
//		// QuestionServiceImpl questionSer = ctx.getBean(QuestionServiceImpl.class);
//		// List<QuestionDTO> questions = questionService.getAllQuestion();
//		AnswerService answerService = ctx.getBean(AnswerService.class);
//		answerService.addAnswer(
//				new AnswerDTO(1, "What?", "smileyface.jpg", false, LocalDateTime.now().toString(), null, null, "user3"));
//		answerService.addAnswer(
//				new AnswerDTO(1, "I know this", "smileyface.jpg", true, LocalDateTime.now().toString(), null, "user1", "user3"));
//		answerService.addAnswer(
//				new AnswerDTO(1, "I doubt it", "smileyface.jpg", true, LocalDateTime.now().toString(), null, "user1", "user3"));
//		answerService.addAnswer(
//				new AnswerDTO(1, "Ha ha ha", "smileyface.jpg", false, LocalDateTime.now().toString(), null, null, "user3"));
//
//		ChatService chatService = ctx.getBean(ChatService.class);
//		chatService.addNewChat(new Chat("user2", "user3", "Hello", "12/29/2023, 1:46:28 PM"));
//		chatService.addNewChat(new Chat("user3", "user2", "How are you?", "12/29/2023, 1:46:28 PM"));
//		chatService.addNewChat(new Chat("user4", "user2", "Say hi to me", "12/29/2023, 1:46:28 PM"));
//		chatService.addNewChat(new Chat("user5", "user2", "Welcome", "12/29/2023, 1:46:28 PM"));
//		chatService.addNewChat(new Chat("user2", "user4", "Do I know you?", "12/29/2023, 1:46:28 PM"));
//		chatService.addNewChat(new Chat("user6", "user2", "I am the ADMIN", "12/29/2023, 1:46:28 PM"));
//		// create directories for images
//		for (String dir : new String[] { "question_images", "answer_images" }) {
//			Path directory = Paths.get("C:/CapstoneProjectFullStack/CapstoneProjectG6_FrontEnd/src/assets/" + dir);
//			if (Files.exists(directory))
//				try (Stream<Path> pathStream = Files.walk(directory)) {
//					pathStream.sorted(Comparator.reverseOrder()).forEach(path -> {
//						try {
//							Files.delete(path);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					});
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			try {
//				Files.createDirectories(directory);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
