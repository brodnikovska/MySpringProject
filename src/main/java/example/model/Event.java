package example.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Event {

    @Column(unique=true)
    private long id;
    private String title;
    private Date date;
}
