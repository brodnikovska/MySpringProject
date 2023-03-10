package example.service;

import example.dao.EventDao;
import example.model.Event;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Setter
@NoArgsConstructor
@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    public Event updateEvent (Event event) {
        return eventDao.update(event);
    }

    public boolean deleteEvent(long eventId) {
        eventDao.deleteById(eventId);
        return true;
    }

    public Event getEventById(long id) throws NoSuchElementException {
        return eventDao
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No event with id %d is present", id)));
    }

    public List<Event> getEventsByTitle(String title) {
        return eventDao.findByTitle(title);
    }

    public List<Event> getEventsForDay(OffsetDateTime localDateTime) {
        return eventDao.findByDate(localDateTime);
    }
}
