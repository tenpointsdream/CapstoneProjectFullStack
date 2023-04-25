package cogent.com.repository;

import cogent.com.util.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	List<User> findAll(String userType);

	User addUser(User user);

	boolean verifyLogin(User user);

	List<User> findAllUserById(int id);

	List<User> getAllUsersByUserType(UserType userType);

	List<User> findByUserType(UserType userType);
}
