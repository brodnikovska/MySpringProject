package example.dao;

import example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws Exception;
    User createUser(User user);
    User updateUser(User user);
    boolean deleteUser(long id);
    Optional<User> getUserById(long id);
    Optional<User> getUserByEmail(String email);
    List<User> getUsersByName(String name);
}
