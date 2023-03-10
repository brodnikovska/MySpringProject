package example.dao;

import example.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    default User update(User user) {
        if (findById(user.getId()).isPresent()) {
            return save(user);
        } else {
            throw new EntityNotFoundException("User with id " + user.getId() + " does not exist");
        }
    }
    Optional<User> findByEmailIgnoreCase(String email);
    List<User> findByNameIgnoreCase(String name);
}
