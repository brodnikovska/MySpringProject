package example.dao;

import example.model.Event;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventDao {

    List<Event> findAll() throws Exception;
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
    Optional<Event> getEventById(long id);
    List<Event> getEventsByTitle(String title);
    List<Event> getEventsForDay(OffsetDateTime localDateTime);
}
