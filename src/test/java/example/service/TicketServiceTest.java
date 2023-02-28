package example.service;

import example.dao.TicketDao;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testBookTicket() {
        Ticket ticket = ticketService.bookTicket(111, 222, 12, Ticket.Category.PREMIUM);

        when(ticketDao.bookTicket(any(Long.class), any(Long.class), any(Integer.class), any(Ticket.Category.class))).thenReturn(ticket);

        Ticket newTicket = ticketService.bookTicket(0, 0, 0, Ticket.Category.PREMIUM);

        assertEquals(ticket, newTicket);
    }

    @Test
    public void testCancelTicket() {
        when(ticketDao.cancelTicket(any(Long.class))).thenReturn(true);

        boolean isCancelled = ticketService.cancelTicket(0);

        assertEquals(true, isCancelled);
    }

    @Test
    public void testGetBookedByUserTickets() {
        Ticket ticket = ticketService.bookTicket(111, 222, 12, Ticket.Category.PREMIUM);
        when(ticketDao.getBookedTickets(any(User.class))).thenReturn(new ArrayList<>(Arrays.asList(ticket)));

        List<Ticket> retrievedTicket = ticketService.getBookedTickets(new User());

        assertEquals(ticket, retrievedTicket.get(0));
    }

    @Test
    public void testGetBookedForEventTickets() {
        Ticket ticket = ticketService.bookTicket(111, 222, 12, Ticket.Category.PREMIUM);
        when(ticketDao.getBookedTickets(any(Event.class))).thenReturn(new ArrayList<>(Arrays.asList(ticket)));

        List<Ticket> retrievedTicket = ticketService.getBookedTickets(new Event());

        assertEquals(ticket, retrievedTicket.get(0));
    }
}
