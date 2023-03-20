package example.service.impl;

import example.dao.repository.EventRepository;
import example.model.Event;
import example.service.EventService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Inject
    private EventRepository eventRepository;


    @Override
    public Event getEventById(long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("No event with id %d is present", id)));
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(OffsetDateTime localDateTime, int pageSize, int pageNum) {
        return eventRepository.findByDate(localDateTime);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        if (eventRepository.findById(event.getId()).isEmpty()) {
            throw new JpaObjectRetrievalFailureException(new EntityNotFoundException("Event with id " + event.getId() + " does not exist"));
        } else {
            return eventRepository.save(event);
        }
    }

    @Override
    public boolean deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
        return true;
    }
}
