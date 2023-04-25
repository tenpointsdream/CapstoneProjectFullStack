package cogent.com.service;

import cogent.com.entity.User;

import java.util.List;

public interface UserService {
	public void addUser(User user);

	public void updateUser(User user);

	public List<User> getAllUsers();

	public void home();

	public void addNewUser(User user);

	public List<String> getLogin(User user);

	public boolean userLoginVerify(User user);

	public List<User> getAllUsersById(int id);

	public List<User> getAllUsersByName(String name);

	public List<User> getAllUsersByUserType(String userType);

	public User getUserById(int id);

	public User getUserByName(String username);
}
