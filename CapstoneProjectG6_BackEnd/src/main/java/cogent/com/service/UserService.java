package cogent.com.service;

import cogent.com.entity.User;

import java.util.List;

public interface UserService {
<<<<<<< HEAD
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
=======
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
>>>>>>> 73fb061d4cbeb95c610b24726809b542bc29f1e4
}
