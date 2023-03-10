package example.service.impl;

import example.dao.UserDao;
import example.model.User;
import example.service.UserService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;


    @Override
    public User getUserById(long id) {
        return userDao
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No user with id %d is present", id)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmailIgnoreCase(email).orElseThrow();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDao.findByNameIgnoreCase(name);
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        userDao.deleteById(userId);
        return true;
    }
}
