package example.service;

import example.dao.TicketDao;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
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

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketDao.bookTicket(userId, eventId, place, category);
    }

    public boolean cancelTicket(long ticketId) {
        return ticketDao.cancelTicket(ticketId);
    }

    public List<Ticket> getBookedTickets(User user) {
        return ticketDao.getBookedTickets(user);
    }

    public List<Ticket> getBookedTickets(Event event) {
        return ticketDao.getBookedTickets(event);
    }
}
