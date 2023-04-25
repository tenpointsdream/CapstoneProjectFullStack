package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.User;

<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

	User findByName(String name);

	List<User> findAll(String userType);
=======
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);

    List<User> findAll(String userType);
    User addUser(User user);

    boolean verifyLogin(User user);

    List<User> findAllUserById(int id);

    Object findByUserName(String name);

    List<User> findByType(String userType);

>>>>>>> 73fb061d4cbeb95c610b24726809b542bc29f1e4
}
