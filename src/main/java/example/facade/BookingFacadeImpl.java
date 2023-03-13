package example.facade;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.service.impl.EventServiceImpl;
import example.service.impl.TicketServiceImpl;
import example.service.impl.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @Override
    public Event getEventById(long id) {
        return eventServiceImpl.getEventById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventServiceImpl.getEventsByTitle(title, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(OffsetDateTime localDateTime, int pageSize, int pageNum) {
        return eventServiceImpl.getEventsForDay(localDateTime, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        return eventServiceImpl.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventServiceImpl.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventServiceImpl.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long id){
        return userServiceImpl.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userServiceImpl.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userServiceImpl.getUsersByName(name);
    }

    @Override
    public User createUser(User user) {
        return userServiceImpl.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userServiceImpl.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userServiceImpl.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketServiceImpl.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketServiceImpl.getBookedTickets(user, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketServiceImpl.getBookedTickets(event, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketServiceImpl.cancelTicket(ticketId);
    }
}
