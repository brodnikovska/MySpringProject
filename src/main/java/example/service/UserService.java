package example.service;

import example.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@NoArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        return userDao
                .getUserById(id)
                .orElseThrow();
    }


    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email).orElseThrow();
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getUsersByName(name);
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(long userId) {
        return userDao.deleteUser(userId);
    }


}
