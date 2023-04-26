package cogent.com.service;

import java.util.List;
import java.util.Optional;

import cogent.com.entity.User;
import cogent.com.util.UserType;

public interface UserService {

	public User addUser(User user);
	public User updateUser(User user);
	public List<User> getAllUsers();
	public User addNewUser(User user);
	public boolean userLoginVerify(User user);
	public Optional<User> getUserById(int id);
	public List<User> getUsersByName(String name);
	public List<User> getUsersByType(UserType userType);

}