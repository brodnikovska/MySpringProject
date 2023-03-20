package example.service;

import example.model.Event;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventService {

    Event getEventById(long id);
    List<Event> getEventsByTitle(String title, int pageSize, int pageNum);
    List<Event> getEventsForDay(OffsetDateTime localDateTime, int pageSize, int pageNum);
    Event createEvent(Event event);
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}
