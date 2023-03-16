package example.service.impl;

import example.dao.repository.UserRepository;
import example.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User stepan = new User("Stepan", "stepan@email");

        when(userRepository.save(any(User.class))).thenReturn(stepan);

        assertEquals(userServiceImpl.createUser(stepan), stepan);
    }

    @Test
    public void testUpdateUser() {
        User stepan = new User("Stepan", "stepan@email");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(stepan));
        when(userRepository.save(any(User.class))).thenReturn(stepan);

        assertEquals(stepan, userServiceImpl.updateUser(stepan));
    }

    @Test
    public void testUpdateUserNotFound() {
        User stepan = new User("Stepan", "stepan@email");
        stepan.setId(1);

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            userServiceImpl.updateUser(stepan);
        } catch (EntityNotFoundException e) {
            assertEquals("User with id 1 does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteUser() {
        long userId = 1;

        doNothing().when(userRepository).deleteById(anyLong());

        boolean result = userServiceImpl.deleteUser(userId);

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testGetUserById() {
        long userId = 1;
        User stepan = new User("Stepan", "stepan@email");
        stepan.setId(userId);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(stepan));

        assertEquals(stepan, userServiceImpl.getUserById(userId));
    }

    @Test
    public void testGetUserByIdNotFound() {
        long userId = 1;
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            userServiceImpl.getUserById(userId);
        } catch (NoSuchElementException e) {
            assertEquals("No user with id 1 is present", e.getMessage());
        }
    }

    @Test
    public void testGetUserByEmail() {
        User stepan = new User("Stepan", "stepan@email");

        when(userRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(stepan));

        assertEquals(stepan, userServiceImpl.getUserByEmail("stepan@email"));
    }

    @Test
    public void testGetUsersByName() {
        User ivan = new User("Ivan", "ivan@email");

        when(userRepository.findByNameIgnoreCase(any(String.class))).thenReturn(new ArrayList<>(List.of(ivan)));

        List<User> retrievedUser = userServiceImpl.getUsersByName("ivan");

        assertEquals(ivan, retrievedUser.get(0));
    }
}
