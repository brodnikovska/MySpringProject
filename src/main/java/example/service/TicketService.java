package example.service;

import example.model.Event;
import example.model.Ticket;
import example.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);
    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);
    boolean cancelTicket(long ticketId);
}
