package example.dao;

import example.model.Event;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventDao extends JpaRepository<Event, Long> {

    default Event update(Event event) {
        if (findById(event.getId()).isPresent()) {
            return save(event);
        } else {
            throw new EntityNotFoundException("User with id " + event.getId() + " does not exist");
        }
    }
    List<Event> findByTitle(String title);
    List<Event> findByDate(OffsetDateTime localDateTime);
}
