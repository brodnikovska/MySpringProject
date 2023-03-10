package example.service;

import example.dao.TicketDao;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@NoArgsConstructor
@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    public Ticket bookTicket(User user, Event event, int place, Ticket.Category category) {
        Ticket ticket = new Ticket(user, event, place, category);
        return ticketDao.save(ticket);
    }

    public boolean cancelTicket(long ticketId) {
        ticketDao.deleteById(ticketId);
        return true;
    }

    public List<Ticket> getBookedTickets(User user) {
        return ticketDao.findByUserId(user.getId());
    }

    public List<Ticket> getBookedTickets(Event event) {
        return ticketDao.findByEventId(event.getId());
    }
}
