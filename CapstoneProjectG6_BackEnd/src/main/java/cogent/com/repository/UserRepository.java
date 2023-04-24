package cogent.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.com.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUserName(String username);

    User addUser(User user);

}
