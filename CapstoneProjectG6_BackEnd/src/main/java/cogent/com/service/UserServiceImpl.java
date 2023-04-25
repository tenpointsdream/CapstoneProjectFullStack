package cogent.com.service;

import cogent.com.entity.User;
import cogent.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return userRepository.save( newUser);
    }

    // TODO: 4/24/23  
    @Override
    public User getLogin() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUsername(userName);
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
}
