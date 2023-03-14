package example.service.impl;

import example.dao.EventDao;
import example.dao.TicketDao;
import example.dao.UserAccountDao;
import example.dao.UserDao;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.service.TicketService;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Inject
    private TicketDao ticketDao;

    @Inject
    private UserDao userDao;

    @Inject
    private EventDao eventDao;

    @Inject
    private UserAccountDao userAccountDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        User user = userDao.findById(userId).orElseThrow();
        Event event = eventDao.findById(eventId).orElseThrow();
        BigDecimal eventPrice = event.getTicketPrice();
        BigDecimal moneyOnAccount = userAccountDao.getAmountOfMoney(user);
        if (eventPrice.compareTo(moneyOnAccount) >= 0) {
            throw new ArithmeticException("User has not enough money on account");
        }
        userAccountDao.withdrawMoney(user, eventPrice);
        Ticket ticket = new Ticket(user, event, place, category);
        return ticketDao.save(ticket);

    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketDao.findByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketDao.findByEventId(event.getId());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        ticketDao.deleteById(ticketId);
        return true;
    }
}
