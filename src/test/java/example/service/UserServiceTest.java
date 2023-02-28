package example.service;

import example.dao.UserDao;
import example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User ostap = new User(333, "Ostap", "ostap@email");

        when(userDao.createUser(any(User.class))).thenReturn(ostap);

        User newUser = userService.createUser(new User());

        assertEquals(ostap.getName(), newUser.getName());
    }

    @Test
    public void testUpdateUser() {
        User olga = new User(333, "Olga", "olga@email");

        when(userDao.updateUser(any(User.class))).thenReturn(olga);

        User updatedUser = userService.updateUser(new User());

        assertEquals(olga.getName(), updatedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        when(userDao.deleteUser(any(Long.class))).thenReturn(true);

        boolean isRemoved = userService.deleteUser(0);

        assertEquals(true, isRemoved);
    }

    @Test
    public void testGetUserById() {
        User ivan = new User(444, "Ivan", "ivan@email");
        when(userDao.getUserById(any(Long.class))).thenReturn(Optional.of(ivan));

        User retrievedUser = userService.getUserById(0);

        assertEquals(ivan, retrievedUser);
    }

    @Test
    public void testGetUserByEmail() {
        User ivan = new User(444, "Ivan", "ivan@email");
        when(userDao.getUserByEmail(any(String.class))).thenReturn(Optional.of(ivan));

        User retrievedUser = userService.getUserByEmail("ivan@email");

        assertEquals(ivan, retrievedUser);
    }

    @Test
    public void testGetUsersByName() {
        User ivan = new User(444, "Ivan", "ivan@email");
        when(userDao.getUsersByName(any(String.class))).thenReturn(new ArrayList<>(Arrays.asList(ivan)));

        List<User> retrievedUser = userService.getUsersByName("ivan");

        assertEquals(ivan, retrievedUser.get(0));
    }
}
