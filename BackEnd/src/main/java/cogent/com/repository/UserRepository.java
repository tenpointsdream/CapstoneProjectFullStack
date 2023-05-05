package cogent.com.repository;

import cogent.com.entity.User;
import cogent.com.util.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	List<User> findByName(String name);
	List<User> findByUserType(UserType userType);
}
