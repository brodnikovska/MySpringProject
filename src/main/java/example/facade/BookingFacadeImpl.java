package example.facade;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.service.EventService;
import example.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private UserService userService;
//    @Autowired
//    private TicketService ticketService;
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

    @SneakyThrows
    @Override
    public User getUserById(long id){
        return userService.getUserById(id);
    }

    @SneakyThrows
    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @SneakyThrows
    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name, pageSize, pageNum);
    }

    @SneakyThrows
    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @SneakyThrows
    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @SneakyThrows
    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}