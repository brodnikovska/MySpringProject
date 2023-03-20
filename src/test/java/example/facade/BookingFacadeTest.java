package example.facade;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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
    private BookingFacade bookingFacade;

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.of(LocalDateTime.of(2023, 4, 10, 19, 0), ZoneOffset.UTC);

    private static final BigDecimal TICKET_PRICE = new BigDecimal(300);

    @Test
    public void testCancelTicket() {
        User stepan = new User("Stepan", "Stepan7@email");
        User createdUser = bookingFacade.createUser(stepan);

        User retrievedUser = bookingFacade.getUserById(stepan.getId());

        assertNotNull(retrievedUser);
        assertEquals(stepan.getId(), retrievedUser.getId());
        assertEquals(stepan.getName(), retrievedUser.getName());
        assertEquals(stepan.getEmail(), retrievedUser.getEmail());

        UserAccount userAccount= new UserAccount(createdUser, new BigDecimal("3000"));
        bookingFacade.createUserAccount(userAccount);
        createdUser.setUserAccount(userAccount);
        bookingFacade.updateUser(createdUser);

        Event karmen = new Event("Karmen7", OFFSET_DATE_TIME, TICKET_PRICE);
        Event createdEvent = bookingFacade.createEvent(karmen);

        Event retrievedEvent = bookingFacade.getEventById(karmen.getId());

        assertNotNull(retrievedEvent);
        assertEquals(karmen.getId(), retrievedEvent.getId());
        assertEquals(karmen.getTitle(), retrievedEvent.getTitle());

        Ticket ticket = bookingFacade.bookTicket(createdUser.getId(), createdEvent.getId(), 6, Ticket.Category.PREMIUM);
        long obtainedTicketId = ticket.getId();

        List<Ticket> retrivedByUserTickets = bookingFacade.getBookedTickets(stepan, 1, 1);
        List<Ticket> retrivedByEventTickets = bookingFacade.getBookedTickets(karmen, 1, 1);

        assertEquals(1, retrivedByUserTickets.size());
        assertEquals(1, retrivedByEventTickets.size());
        assertEquals(ticket.toString(), retrivedByUserTickets.get(0).toString());
        assertEquals(ticket.toString(), retrivedByEventTickets.get(0).toString());

        bookingFacade.cancelTicket(obtainedTicketId);

        retrivedByUserTickets = bookingFacade.getBookedTickets(stepan, 1, 1);
        retrivedByEventTickets = bookingFacade.getBookedTickets(karmen, 1, 1);

        assertEquals(0, retrivedByUserTickets.size());
        assertEquals(0, retrivedByEventTickets.size());
    }
}

