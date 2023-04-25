package cogent.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

	User findByName(String name);

	List<User> findAll(String userType);
}
