package example.service;

import example.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import example.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
public class UserService {

    private UserDao userDao;

    public User getUserById(long id) throws Exception {
        List<User> users = userDao.findAll();
        User foundUser = new User(0, null, null);

        for (User user : users) {
            long userId = user.getId();

            if (userId == id) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public User getUserByEmail(String email) throws Exception {
        List<User> users = userDao.findAll();
        User foundUser = new User(0, null, null);

        for (User user : users) {
            String userEmail = user.getEmail();

            if (userEmail.equalsIgnoreCase(email)) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) throws Exception {
        List<User> foundUsers = new ArrayList<>();
        List<User> users = userDao.findAll();

        for (User user : users) {
            String userName = user.getName();

            if (userName.equalsIgnoreCase(name)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    public User createUser(User user) throws Exception {
        List<User> users = userDao.findAll();
        if (!users.contains(user)) {
            /**
             * TO DO: put new user to storage
             * */
            return user;
        } else return new User(0, null, null);
    }

    public User updateUser(User user) throws Exception {
        List<User> users = userDao.findAll();
        User updatedUser = new User(0, null, null);
        for (User userItem : users) {
            if (userItem.getId() == user.getId() && userItem.getEmail().equalsIgnoreCase(user.getEmail())) {
                /**
                 * TO DO: replace existing user in a storage with new fields
                 * */
                updatedUser = user;
            }
        }
        return updatedUser;
    }

    public boolean deleteUser(long userId) throws Exception {
        List<User> users = userDao.findAll();
        for (User userItem : users) {
            if (userItem.getId() == userId) {
                /**
                 * TO DO: implement functionality
                 * */
            }
        }
        return false;
    }


}
