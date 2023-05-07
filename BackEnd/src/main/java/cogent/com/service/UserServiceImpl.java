package cogent.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import cogent.com.util.UserType;
import static cogent.com.util.AppUtil.sha256;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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
		user.setPassword(sha256(user.getPassword()));
		return userRepository.save(user);
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
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
