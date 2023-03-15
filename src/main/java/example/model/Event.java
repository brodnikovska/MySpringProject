package example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    private BigDecimal ticketPrice;

    public Event(String title, OffsetDateTime date, BigDecimal ticketPrice) {
        this.title = title;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
