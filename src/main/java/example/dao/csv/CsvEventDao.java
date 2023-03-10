//package example.dao.csv;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import example.dao.EventDao;
//import example.dao.Storage;
//import example.exception.DuplicateException;
//import example.model.Event;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import java.time.OffsetDateTime;
//
//import java.util.*;
//
//@Setter
//@NoArgsConstructor
//@Component
//public class CsvEventDao implements EventDao {
//
//    @Autowired
//    private Storage storage;
//    private static final String ID = "\"event:%s\"";
//
//    private String formatEventKey(Event event) {
//        return String.format(ID, event.getId());
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Event> findAll() {
//        List<Event> foundEvents = new ArrayList<>();
//        for (Map.Entry<String, String> item : storage.getAllItems().entrySet()) {
//            if (item.toString().startsWith("\"event:")) {
//                String entryValue = item.getValue();
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.registerModule(new JavaTimeModule());
//                Event eventItem = mapper.readValue(entryValue, Event.class);
//                foundEvents.add(eventItem);
//            }
//        }
//        return foundEvents;
//    }
//
//    @SneakyThrows
//    @Override
//    public Event createEvent(Event event) {
//        String newItem = new ObjectMapper().writeValueAsString(event);
//        if (storage.isItemPresent(formatEventKey(event))) {
//            throw new DuplicateException(String.valueOf(event.getId()));
//        }
//        storage.putItem(formatEventKey(event), newItem);
//        return event;
//    }
//
//    @SneakyThrows
//    @Override
//    public boolean deleteEvent(long id) {
//        return storage.deleteItem(String.format(ID, id));
//    }
//
//    @SneakyThrows
//    @Override
//    public Event update(Event event) {
//        String newItem = new ObjectMapper().writeValueAsString(event);
//        if (storage.isItemPresent(formatEventKey(event))) {
//            storage.updateItem(formatEventKey(event), newItem);
//        }
//        return event;
//    }
//
//    @SneakyThrows
//    @Override
//    public Optional<Event> getEventById(long id) {
//        List<Event> allEvents = findAll();
//        return allEvents.stream()
//                .filter(item -> item.getId() == id)
//                .findFirst();
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Event> getEventsForDay(OffsetDateTime localDateTime) {
//        List<Event> allEvents = findAll();
//        return allEvents.stream().filter(item -> item.getDate().equals(localDateTime)).toList();
//    }
//
//    @SneakyThrows
//    @Override
//    public List<Event> getEventsByTitle(String title) {
//        List<Event> allEvents = findAll();
//        return allEvents.stream().filter(item -> item.getTitle().equalsIgnoreCase(title)).toList();
//    }
//}
