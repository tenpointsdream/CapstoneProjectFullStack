package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);

    List<User> findAll(String userType);
    User addUser(User user);

    boolean verifyLogin(User user);

    List<User> findAllUserById(int id);

    Object findByUserName(String name);

    List<User> findByType(String userType);

}
