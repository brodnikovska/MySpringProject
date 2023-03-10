package example.service;

import example.model.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import lombok.Setter;
import example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Setter
@NoArgsConstructor
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User createUser(User user) {
        return userDao.save(user);
    }

    public User updateUser(User user) {
        return userDao.update(user);
    }

    public boolean deleteUser(long userId) {
        userDao.deleteById(userId);
        return true;
    }

    public User getUserById(long id) throws NoSuchElementException {
        return userDao
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No user with id %d is present", id)));
    }

    public User getUserByEmail(String email) {
        return userDao.findByEmailIgnoreCase(email).orElseThrow();
    }

    public List<User> getUsersByName(String name) {
        return userDao.findByNameIgnoreCase(name);
    }
}
