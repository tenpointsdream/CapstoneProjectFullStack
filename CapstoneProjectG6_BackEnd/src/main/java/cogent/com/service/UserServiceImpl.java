package cogent.com.service;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void home() {
		System.out.println("Home Page");
	}

	@Override
	public void addNewUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<String> getLogin(User user) {
		List<String> userCredentials = new ArrayList<String>();
		userCredentials.add(user.getUsername());
		userCredentials.add(user.getPassword());
		return userCredentials;
	}

	@Override
	public boolean userLoginVerify(User user) {
		return userRepository.verifyLogin(user);
	}

	@Override
	public List<User> getAllUsersById(int id) {
		return userRepository.findAllUserById(id);
	}

	@Override
	public List<User> getAllUsersByName(String name) {
		return (List<User>) userRepository.findAll(name);
	}

	@Override
	public List<User> getAllUsersByUserType(String userType) {
		return userRepository.findByType(userType);
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User getUserByName(String username) {
		return userRepository.findByUsername(username);
	}
}
