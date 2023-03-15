package example.dao.repository;

import example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmailIgnoreCase() {
        User user = new User();
        user.setName("User A");
        user.setEmail("User_A@email");
        userRepository.save(user);

        User foundUser = userRepository.findByEmailIgnoreCase("user_a@email").orElseThrow();

        assertEquals("User_A@email", foundUser.getEmail());
    }

    @Test
    public void testFindByNameIgnoreCase() {
        User user = new User();
        user.setName("User A");
        user.setEmail("User_A@email");
        userRepository.save(user);

        User foundUser = userRepository.findByNameIgnoreCase("user a").get(userRepository.findByNameIgnoreCase("user a").size() - 1);

        assertEquals("User A", foundUser.getName());
    }
}
