package example.service;

import example.dao.EventDao;
import example.dao.UserDao;
import example.model.Event;
import example.model.User;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
@NoArgsConstructor
@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public Event createEvent(Event event) {
        return eventDao.createEvent(event);
    }

    public Event updateEvent (Event event) {
        return eventDao.updateEvent(event);
    }

    public boolean deleteEvent(long eventId) {
        return eventDao.deleteEvent(eventId);
    }

    public Event getEventById(long id) throws NoSuchElementException {
        return eventDao
                .getEventById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No event with id %d is present", id)));
    }

    public List<Event> getEventsByTitle(String title) {
        return eventDao.getEventsByTitle(title);
    }

    public List<Event> getEventsForDay(OffsetDateTime localDateTime) {
        return eventDao.getEventsForDay(localDateTime);
    }
}
