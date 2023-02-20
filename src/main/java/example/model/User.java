package example.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    @Column(unique=true)
    private long id;
    private String name;
    @Column(unique=true)
    private String email;
}
