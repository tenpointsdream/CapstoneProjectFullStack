package cogent.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import cogent.com.util.UserType;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User addNewUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean userLoginVerify(User user) {
		return false;
	}

	@Override
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> getUsersByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> getUsersByType(UserType userType) {
		return userRepository.findByUserType(userType);
	}

	@Override
	public String home() {
		return "Users Home Page";
	}

}
