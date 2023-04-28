package cogent.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cogent.com.entity.User;
import cogent.com.util.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	List<User> findByName(String name);
	List<User> findByUserType(UserType userType);
	User findByUsernameAndPassword(String username, String password);
}
