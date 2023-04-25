package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUserName(String userName);

    List<User> findAll(String userType);
    User addUser(User user);

}
