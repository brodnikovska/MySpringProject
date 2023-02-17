package example.dao.jdbc;

import example.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import example.dao.UserDao;

import javax.sql.DataSource;
import java.util.List;

@NoArgsConstructor
@Setter
public class JdbcUserDao implements UserDao {

    private DataSource dataSource;

    @Override
    public List<User> findAll() throws Exception {
        return (List<User>) new UnsupportedOperationException("This method has not been implemented");
    }
}