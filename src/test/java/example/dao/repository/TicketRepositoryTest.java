package example.dao.repository;

import example.model.Event;
import example.model.Ticket;
import example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TicketRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.now();
    private static final BigDecimal TICKET_PRICE = new BigDecimal(300);

    @Test
    public void testFindByUserId() {
        User user = new User();
        user.setName("User F");
        user.setEmail("User_F@email");
        userRepository.save(user);

        Event ballet = new Event();
        ballet.setTitle("Ballet F");
        ballet.setDate(OFFSET_DATE_TIME);
        ballet.setTicketPrice(TICKET_PRICE);
        eventRepository.save(ballet);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(ballet);
        ticket.setPlace(25);
        ticket.setCategory(Ticket.Category.PREMIUM);
        ticketRepository.save(ticket);

        Ticket foundTicket = ticketRepository.findByUserId(user.getId()).get(0);

        assertEquals(ticket, foundTicket);
    }

    @Test
    public void testFindByEventId() {
        User user = new User();
        user.setName("User H");
        user.setEmail("User_H@email");
        userRepository.save(user);

        Event ballet = new Event();
        ballet.setTitle("Ballet H");
        ballet.setDate(OFFSET_DATE_TIME);
        ballet.setTicketPrice(TICKET_PRICE);
        eventRepository.save(ballet);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(ballet);
        ticket.setPlace(14);
        ticket.setCategory(Ticket.Category.PREMIUM);
        ticketRepository.save(ticket);

        Ticket foundTicket = ticketRepository.findByEventId(ballet.getId()).get(0);

        assertEquals(ticket, foundTicket);
    }
}
