package example.dao;

import example.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll() throws Exception;
}
