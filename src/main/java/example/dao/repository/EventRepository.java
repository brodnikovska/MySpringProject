package example.dao.repository;

import example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByTitle(String title);
    List<Event> findByDate(OffsetDateTime localDateTime);
}
