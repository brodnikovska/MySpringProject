package example.service.impl;

import example.dao.repository.UserRepository;
import example.model.User;
import example.service.UserService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;


    @Override
    public User getUserById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No user with id %d is present", id)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepository.findByNameIgnoreCase(name);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new EntityNotFoundException("User with id " + user.getId() + " does not exist");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public boolean deleteUser(long userId) {
        userRepository.deleteById(userId);
        return true;
    }
}
