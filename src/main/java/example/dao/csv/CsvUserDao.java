package example.dao.csv;

import example.dao.UserDao;
import example.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Setter
@NoArgsConstructor
@Component
public class CsvUserDao implements UserDao {

    private Resource csvResource;

    @Override
    public List<User> findAll() throws Exception {
        List<User> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(csvResource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                long userId = Long.parseLong(fields[0]);
                String name = fields[1];
                String email = fields[2];

                User user =
                        new User(userId, name, email);
                results.add(user);
            }

        } catch (Exception exception) {
            exception.getMessage();
        }
        return results;
    }
}
