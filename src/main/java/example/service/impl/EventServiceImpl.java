package example.service.impl;

import example.dao.EventDao;
import example.model.Event;
import example.service.EventService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Inject
    private EventDao eventDao;


    @Override
    public Event getEventById(long id) {
        return eventDao
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No event with id %d is present", id)));
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventDao.findByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(OffsetDateTime localDateTime, int pageSize, int pageNum) {
        return eventDao.findByDate(localDateTime);
    }

    @Override
    public Event createEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDao.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        eventDao.deleteById(eventId);
        return true;
    }
}
