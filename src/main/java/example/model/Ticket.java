package example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket {

    public enum Category {STANDARD, PREMIUM, BAR};
    @Id
    @Column(unique = true, name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    @NotNull
    private Event event;
    @Column()
    @NotNull
    private int place;
    @Column()
    @NotNull
    private Category category;

    public Ticket(User user, Event event, int place, Category category) {
        this.user = user;
        this.event = event;
        this.place = place;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", place=" + place +
                ", category=" + category +
                '}';
    }
}
