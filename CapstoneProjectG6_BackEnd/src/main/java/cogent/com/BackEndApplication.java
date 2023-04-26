package cogent.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cogent.com.entity.User;
import cogent.com.service.UserService;

import static cogent.com.util.UserType.*;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(BackEndApplication.class, args);
		UserService userService = ctx.getBean(UserService.class);
		userService.addUser(new User("Timmy", "timmy", "timmy123", "timmy@gmail.com", ADMIN));
		userService.addUser(new User("Gyanendra", "gyanendra", "gyanendra123", "gyanendra@gmail.com", USER));
	}

}
