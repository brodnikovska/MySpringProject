package example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import example.utils.CustomOffsetDateTimeSerializer;
import example.utils.CustomOffsetDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @JsonProperty("id")
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("date")
    @JsonSerialize(using = CustomOffsetDateTimeSerializer.class)
    @JsonDeserialize(using = CustomOffsetDateTimeDeserializer.class)
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
