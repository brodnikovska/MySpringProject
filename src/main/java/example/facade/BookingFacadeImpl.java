package example.facade;

import example.aspect.Loggable;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.service.EventService;
import example.service.TicketService;
import example.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private EventService eventService;

    @Override
    public Event getEventById(long id) {
        return eventService.getEventById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(OffsetDateTime localDateTime, int pageSize, int pageNum) {
        return eventService.getEventsForDay(localDateTime);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long id){
        return userService.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        User user = userService.getUserById(userId);
        Event event = eventService.getEventById(eventId);
        return ticketService.bookTicket(user, event, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}
