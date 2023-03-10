//package example.dao.csv;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import example.dao.Storage;
//import example.dao.TicketDao;
//import example.dao.UserDao;
//import example.exception.DuplicateException;
//import example.model.Event;
//import example.model.Ticket;
//import example.model.User;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Setter
//@NoArgsConstructor
//@Component
//public class CsvTicketDao implements TicketDao {
//
//    @Autowired
//    private Storage storage;
//    private static final String ID = "\"ticket:%s\"";
//
//    private String formatTicketKey(Ticket ticket) {
//        return String.format(ID, ticket.getId());
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Ticket> findAll() {
//        List<Ticket> foundTickets = new ArrayList<>();
//        for (Map.Entry<String, String> item : storage.getAllItems().entrySet()) {
//            if (item.toString().startsWith("\"ticket:")) {
//                String entryValue = item.getValue();
//                Ticket ticketItem = new ObjectMapper().readValue(entryValue, Ticket.class);
//                foundTickets.add(ticketItem);
//            }
//        }
//        return foundTickets;
//    }
//
//    @SneakyThrows
//    @Override
//    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
//        Ticket ticket = new Ticket(userId, eventId, place, category);
//        String newItem = new ObjectMapper().writeValueAsString(ticket);
//        if (storage.isItemPresent(formatTicketKey((ticket)))) {
//            throw new DuplicateException(String.valueOf(ticket.getId()));
//        }
//        storage.putItem(formatTicketKey(ticket), newItem);
//        return ticket;
//    }
//
//    @SneakyThrows
//    @Override
//    public boolean cancelTicket(long ticketId) {
//        return storage.deleteItem(String.format(ID, ticketId));
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Ticket> getBookedTickets(User user) {
//        List<Ticket> allTickets = findAll();
//        return allTickets.stream().filter(item -> item.getUserId() == user.getId()).toList();
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Ticket> getBookedTickets(Event event) {
//        List<Ticket> allTickets = findAll();
//        return allTickets.stream().filter(item -> item.getEventId() == event.getId()).toList();
//    }
//}
