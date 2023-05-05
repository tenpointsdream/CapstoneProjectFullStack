package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.User;
import cogent.com.util.UserType;

public interface UserService {
	String home();
	User addUser(User user);
	User updateUser(User user);
	List<User> getAllUsers();
	User addNewUser(User user);
	boolean userLoginVerify(User user);
	Optional<User> getUserById(int id);
	List<User> getUsersByName(String name);
	List<User> getUsersByType(UserType userType);
	Optional<User> getUserByUsername(String username);
}
