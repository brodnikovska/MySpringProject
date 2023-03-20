package example.service.impl;

import example.dao.repository.EventRepository;
import example.dao.repository.TicketRepository;
import example.dao.repository.UserAccountRepository;
import example.dao.repository.UserRepository;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import example.service.TicketService;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketRepository ticketRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private EventRepository eventRepository;

    @Inject
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountServiceImpl userAccountServiceImpl;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        User user = userRepository.findById(userId).orElseThrow();
        Event event = eventRepository.findById(eventId).orElseThrow();
        UserAccount userAccount = userAccountRepository.findByUserId(userId).orElseThrow();
        BigDecimal eventPrice = event.getTicketPrice();
        BigDecimal moneyOnAccount = userAccount.getPrepaidMoney();
        if (eventPrice.compareTo(moneyOnAccount) > 0) {
            throw new ArithmeticException("User has not enough money on account");
        }
        userAccountServiceImpl.withdrawMoney(userId, eventPrice);
        Ticket ticket = new Ticket(user, event, place, category);
        return ticketRepository.save(ticket);

    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketRepository.findByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketRepository.findByEventId(event.getId());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        ticketRepository.deleteById(ticketId);
        return true;
    }
}
