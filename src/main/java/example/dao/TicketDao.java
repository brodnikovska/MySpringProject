package example.dao;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TicketDao extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByEventId(Long eventId);
}
