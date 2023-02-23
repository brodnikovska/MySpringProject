package example.dao.csv;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.dao.Storage;
import example.dao.UserDao;
import example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Setter
@NoArgsConstructor
@Component
public class CsvUserDao implements UserDao {

    @Autowired
    private Storage storage;
    private static final String ID = "\"user:%s\"";
    private static final String EMAIL = "\"email\":\"%s\"";

    @SneakyThrows
    @Override
    public List<User> findAll() {
        List<User> foundUsers = new ArrayList<>();
        for (Map.Entry<String, String> item : storage.getAllItems().entrySet()) {
            if (item.toString().startsWith("\"user:")) {
                String entryValue = item.getValue();
                User userItem = new ObjectMapper().readValue(entryValue, User.class);
                foundUsers.add(userItem);
            }
        }
        return foundUsers;
    }

    @SneakyThrows
    @Override
    public User createUser(User user) {
        String newItem = new ObjectMapper().writeValueAsString(user);
        if (storage.isItemPresent(String.format(ID, user.getId()))) {
            throw new Exception(String.format("User with id %d is already present", user.getId()));
        }
        if (storage.isSomeDataPresent(String.format(EMAIL, user.getEmail()))) {
            throw new Exception(String.format("User with email %s is already present", user.getEmail()));
        }
        storage.putItem(String.format(ID, user.getId()), newItem);
        return user;
    }

    @SneakyThrows
    @Override
    public boolean deleteUser(long id) {
        return storage.deleteItem(String.format(ID, id));
    }

    @SneakyThrows
    @Override
    public User updateUser(User user) {
        String newItem = new ObjectMapper().writeValueAsString(user);
        if (storage.isItemPresent(String.format(ID, user.getId()))) {
            storage.updateItem(String.format(ID, user.getId()), newItem);
        }
        return user;
    }

    @SneakyThrows
    @Override
    public Optional<User> getUserById(long id) {
        List<User> allUsers = findAll();
        return allUsers.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    @SneakyThrows
    @Override
    public Optional<User> getUserByEmail(String email) {
        List<User> allUsers = findAll();
        return allUsers.stream().filter(item -> item.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    @SneakyThrows
    @Override
    public List<User> getUsersByName(String name) {
        List<User> allUsers = findAll();
        return allUsers.stream().filter(item -> item.getName().equalsIgnoreCase(name)).toList();
    }

//    private Resource csvResource;
//
//    @Override
//    public List<User> findAll(){
//        List<User> results = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(
//                new FileReader(csvResource.getFile()))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] fields = line.split(",");
//
//                long userId = Long.parseLong(fields[0]);
//                String name = fields[1];
//                String email = fields[2];
//
//                User user =
//                        new User(userId, name, email);
//                results.add(user);
//            }
//
//        } catch (Exception exception) {
//            exception.getMessage();
//        }
//        return results;
//    }
}
