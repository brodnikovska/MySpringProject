package example.dao;

import example.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDao extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByEventId(Long eventId);
}
