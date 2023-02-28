package example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    private static int nextTicketId = 4;

    public enum Category {STANDARD, PREMIUM, BAR};
    @JsonProperty("id")
    private long id;
    @JsonProperty("userId")
    private long userId;
    @JsonProperty("eventId")
    private long eventId;
    @JsonProperty("place")
    private int place;
    @JsonProperty("category")
    private Category category;

    public Ticket(long userId, long eventId, int place, Category category) {
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
        this.id = nextTicketId;
        nextTicketId++;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", place=" + place +
                ", category=" + category +
                '}';
    }
}
