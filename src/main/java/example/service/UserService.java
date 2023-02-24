package example.service;

import example.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Setter
@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }

    public User getUserById(long id) throws NoSuchElementException {
        return userDao
                .getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No user with id %d is present", id)));
    }


    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email).orElseThrow();
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name);
    }
}
