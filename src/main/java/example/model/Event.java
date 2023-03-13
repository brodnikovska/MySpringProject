package example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EVENTS")
public class Event {

    @Id
    @Column(unique = true, name = "event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column()
    @NotNull
    private String title;
    @Column()
    @NotNull
    private OffsetDateTime date;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
