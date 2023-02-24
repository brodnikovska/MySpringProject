package example.dao;

import example.model.Event;
import example.model.Ticket;
import example.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketDao {

    List<Ticket> findAll() throws Exception;
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user);
    List<Ticket> getBookedTickets(Event event);
    boolean cancelTicket(long ticketId);
}
