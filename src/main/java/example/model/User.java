package example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(unique = true, name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
