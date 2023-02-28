package example.facade;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BookingFacadeTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BookingFacadeImpl bookingFacadeImpl;

    @Test
    public void testCancelTicket() {
        User stepan = new User(1234512345, "Stepan", "Stepan@email");
        bookingFacadeImpl.createUser(stepan);

        User retrievedUser = bookingFacadeImpl.getUserById(stepan.getId());

        assertNotNull(retrievedUser);
        assertEquals(stepan.getId(), retrievedUser.getId());
        assertEquals(stepan.getName(), retrievedUser.getName());
        assertEquals(stepan.getEmail(), retrievedUser.getEmail());

        Event karmen = new Event(1212121212, "Karmen", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
        bookingFacadeImpl.createEvent(karmen);

        Event retrievedEvent = bookingFacadeImpl.getEventById(karmen.getId());

        assertNotNull(retrievedEvent);
        assertEquals(karmen.getId(), retrievedEvent.getId());
        assertEquals(karmen.getTitle(), retrievedEvent.getTitle());
        assertEquals(karmen.getDate(), retrievedEvent.getDate());

        Ticket ticket = bookingFacadeImpl.bookTicket(stepan.getId(), karmen.getId(), 6, Ticket.Category.PREMIUM);
        Long obtainedTicketId = ticket.getId();

        List<Ticket> retrivedByUserTickets = bookingFacadeImpl.getBookedTickets(stepan, 1, 1);
        List<Ticket> retrivedByEventTickets = bookingFacadeImpl.getBookedTickets(karmen, 1, 1);

        assertEquals(1, retrivedByUserTickets.size());
        assertEquals(1, retrivedByEventTickets.size());
        assertEquals(ticket.toString(), retrivedByUserTickets.get(0).toString());
        assertEquals(ticket.toString(), retrivedByEventTickets.get(0).toString());

        bookingFacadeImpl.cancelTicket(obtainedTicketId);

        retrivedByUserTickets = bookingFacadeImpl.getBookedTickets(stepan, 1, 1);
        retrivedByEventTickets = bookingFacadeImpl.getBookedTickets(karmen, 1, 1);

        assertEquals(0, retrivedByUserTickets.size());
        assertEquals(0, retrivedByEventTickets.size());
    }
}

