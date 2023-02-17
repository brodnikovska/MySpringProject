package example.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {

    public enum Category {STANDARD, PREMIUM, BAR};
    @Column(unique=true)
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
