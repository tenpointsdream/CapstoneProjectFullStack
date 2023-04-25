package cogent.com.service;

import cogent.com.entity.User;

import java.util.List;

public interface UserService {
	String home();

	User addUser(User user);

	User addNewUser(User user);

	User getLogin();

	List<User> getAllUsers();

	User getUserById(int userId);

	User updateUser(User user);

	User getUserByName(String name);

	User getUserByUserame(String userName);

	List<User> getAllUsersByType(String userType);

	String userLoginVerify();
}
