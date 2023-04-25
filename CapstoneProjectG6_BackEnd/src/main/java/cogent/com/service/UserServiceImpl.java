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

<<<<<<< HEAD
	// TODO: 4/24/23
	@Override
	public String home() {
		return null;
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User addNewUser(User newUser) {
		return userRepository.save(newUser);
	}

	// TODO: 4/24/23
	@Override
	public User getLogin() {
		return null;
	}
=======
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
>>>>>>> 73fb061d4cbeb95c610b24726809b542bc29f1e4

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

<<<<<<< HEAD
	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsersByType(String userType) {
		return userRepository.findAll(userType);
	}

	// TODO: 4/24/23
	@Override
	public String userLoginVerify() {
		return null;
	}

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User getUserByUserame(String userName) {
		return userRepository.findByUserName(userName);
	}
=======
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
        return (List<User>) userRepository.findByUserName(name);
    }

    @Override
    public List<User> getAllUsersByUserType(String userType) {
        return userRepository.findByType(userType);
    }
>>>>>>> 73fb061d4cbeb95c610b24726809b542bc29f1e4
}
