package example.dao;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import example.service.EventService;
import example.service.TicketService;
import example.service.UserAccountService;
import example.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TicketServiceDaoTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private EventService eventService;

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.of(LocalDateTime.of(2023, 5, 1, 15, 0), ZoneOffset.UTC);
    private static final BigDecimal TICKET_PRICE = new BigDecimal(150);
    private User user, createdUser;
    private Event event, createdEvent;
    private UserAccount userAccount;

    @Before
    public void initialize() {
        user = new User("John S", "john_s2004@email");
        createdUser = userService.createUser(user);

        event = new Event("Norma4", OFFSET_DATE_TIME, TICKET_PRICE);
        createdEvent = eventService.createEvent(event);

        userAccount= new UserAccount(createdUser, new BigDecimal("1000"));
    }

    @Test
    public void testBookTicket() {
        UserAccount createdUserAccount = userAccountService.createUserAccount(userAccount);
        createdUser.setUserAccount(userAccount);
        userService.updateUser(createdUser);

        Ticket ticket = ticketService.bookTicket(createdUser.getId(), createdEvent.getId(), 45, Ticket.Category.BAR);

        BigDecimal userAccountAfterPurchase = userAccountService.getAmountOfMoney(createdUser.getId());

        assertEquals(createdUser.getId(), ticket.getUser().getId());
        assertEquals(createdEvent.getId(), ticket.getEvent().getId());
        assertEquals(userAccountAfterPurchase.stripTrailingZeros(),
                (createdUserAccount.getPrepaidMoney()).subtract(TICKET_PRICE).stripTrailingZeros());

        ticketService.cancelTicket(ticket.getId());
        userAccountService.deleteUserAccount(createdUserAccount);
    }

    @Test
    public void testGetBookedTicketsByUser() {
        createdUser.setUserAccount(userAccount);
        userService.updateUser(createdUser);

        Ticket ticket = ticketService.bookTicket(createdUser.getId(), createdEvent.getId(), 45, Ticket.Category.BAR);

        Ticket retrievedTicket = ticketService.getBookedTickets(createdUser, 10, 1).stream().findAny().orElseThrow();

        assertEquals(ticket.toString(), retrievedTicket.toString());

        ticketService.cancelTicket(retrievedTicket.getId());
    }

    @Test
    public void testGetBookedTicketsByEvent() {
        createdUser.setUserAccount(userAccount);
        userService.updateUser(createdUser);

        Ticket ticket = ticketService.bookTicket(createdUser.getId(), createdEvent.getId(), 45, Ticket.Category.BAR);

        Ticket retrievedTicket = ticketService.getBookedTickets(createdEvent, 10, 1).stream().findAny().orElseThrow();

        assertEquals(ticket.toString(), retrievedTicket.toString());

        ticketService.cancelTicket(retrievedTicket.getId());
    }

    @After
    public void tearDown() {
        userService.deleteUser(createdUser.getId());

        eventService.deleteEvent(createdEvent.getId());
    }
}
