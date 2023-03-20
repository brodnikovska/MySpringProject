package example.service.impl;

import example.dao.repository.EventRepository;
import example.dao.repository.TicketRepository;
import example.dao.repository.UserAccountRepository;
import example.dao.repository.UserRepository;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserAccountServiceImpl userAccountServiceImpl;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Test
    public void testBookTicket() {
        BigDecimal bigDecimal = new BigDecimal(1000);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC);
        Ticket ticket = new Ticket(new User(), new Event(), 12, Ticket.Category.PREMIUM);

        User stepan = new User("Stepan", "stepan@email");
        UserAccount stepanAccount = new UserAccount(stepan, bigDecimal);
        Event karmen = new Event("Karmen", offsetDateTime, bigDecimal);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(stepan));
        when(userAccountRepository.findByUserId(anyLong())).thenReturn(Optional.of(stepanAccount));
        when(userAccountServiceImpl.withdrawMoney(anyLong(), any(BigDecimal.class))).thenReturn(bigDecimal);
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(karmen));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket newTicket = ticketServiceImpl.bookTicket(stepan.getId(), karmen.getId(), 0, Ticket.Category.PREMIUM);

        assertEquals(ticket, newTicket);
    }

    @Test
    public void testBookTicketIfNotEnoughMoneyOnAccount() {
        BigDecimal oneThousand = new BigDecimal(1000);
        BigDecimal one = new BigDecimal(1);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC);

        User stepan = new User("Stepan", "stepan@email");
        UserAccount stepanAccount = new UserAccount(stepan, oneThousand);
        Event karmen = new Event("Karmen", offsetDateTime, oneThousand.add(one));

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(stepan));
        when(userAccountRepository.findByUserId(anyLong())).thenReturn(Optional.of(stepanAccount));
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(karmen));

        try {
            ticketServiceImpl.bookTicket(stepan.getId(), karmen.getId(), 0, Ticket.Category.PREMIUM);
        } catch (ArithmeticException exception) {
            assertEquals("User has not enough money on account", exception.getMessage());
        }
    }

    @Test
    public void testCancelTicket() {
        long ticketId = 1;
        doNothing().when(ticketRepository).deleteById(anyLong());

        boolean isRemoved = ticketServiceImpl.cancelTicket(ticketId);

        assertTrue(isRemoved);
        verify(ticketRepository, times(1)).deleteById(ticketId);
    }

    @Test
    public void testGetBookedByUserTickets() {
        Ticket ticket = new Ticket(new User(), new Event(), 12, Ticket.Category.PREMIUM);

        when(ticketRepository.findByUserId(anyLong())).thenReturn(new ArrayList<>(List.of(ticket)));

        List<Ticket> retrievedTicket = ticketServiceImpl.getBookedTickets(new User(), 10, 1);

        assertEquals(ticket, retrievedTicket.get(0));
    }

    @Test
    public void testGetBookedForEventTickets() {
        Ticket ticket = new Ticket(new User(), new Event(), 12, Ticket.Category.PREMIUM);

        when(ticketRepository.findByEventId(anyLong())).thenReturn(new ArrayList<>(List.of(ticket)));

        List<Ticket> retrievedTicket = ticketServiceImpl.getBookedTickets(new Event(), 10, 1);

        assertEquals(ticket, retrievedTicket.get(0));
    }
}
