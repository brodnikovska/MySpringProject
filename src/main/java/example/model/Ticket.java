package example.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    public enum Category {STANDARD, PREMIUM, BAR};
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
