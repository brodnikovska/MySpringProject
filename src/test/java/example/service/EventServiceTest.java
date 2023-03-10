//package example.service;
//
//import example.dao.EventDao;
//import example.model.Event;
//import example.model.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EventServiceTest {
//
//    @Mock
//    private EventDao eventDao;
//
//    @InjectMocks
//    private EventService eventService;
//
//    @Test
//    public void testCreateEvent() {
//        Event turandot = new Event(333, "Turandot", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//
//        when(eventDao.createEvent(any(Event.class))).thenReturn(turandot);
//
//        Event newEvent = eventService.createEvent(new Event());
//
//        assertEquals(turandot, newEvent);
//    }
//
//    @Test
//    public void testUpdateEvent() {
//        Event flyingCircus = new Event(444, "Flying circus", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//
//        when(eventDao.update(any(Event.class))).thenReturn(flyingCircus);
//
//        Event updatedEvent = eventService.update(new Event());
//
//        assertEquals(flyingCircus, updatedEvent);
//    }
//
//    @Test
//    public void testDeleteEvent() {
//        when(eventDao.deleteEvent(any(Long.class))).thenReturn(true);
//
//        boolean isRemoved = eventService.deleteEvent(0);
//
//        assertEquals(true, isRemoved);
//    }
//
//    @Test
//    public void testGetEventById() {
//        Event newYearParty = new Event(777, "New Year celebration", OffsetDateTime.of(LocalDateTime.of(2023, 1, 1, 0, 0), ZoneOffset.UTC));
//        when(eventDao.getEventById(any(Long.class))).thenReturn(Optional.of(newYearParty));
//
//        Event retrievedEvent = eventService.getEventById(0);
//
//        assertEquals(newYearParty, retrievedEvent);
//    }
//
//    @Test
//    public void testGetEventsByTitle() {
//        Event karmen = new Event(99, "Karmen", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//        when(eventDao.getEventsByTitle(any(String.class))).thenReturn(new ArrayList<>(Arrays.asList(karmen)));
//
//        List<Event> retrievedEvent = eventService.getEventsByTitle("Karmen");
//
//        assertEquals(karmen, retrievedEvent.get(0));
//    }
//
//    @Test
//    public void testGetEventsForDay() {
//        Event karmen = new Event(99, "Karmen", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//        when(eventDao.getEventsForDay(any(OffsetDateTime.class))).thenReturn(new ArrayList<>(Arrays.asList(karmen)));
//
//        List<Event> retrievedEvent = eventService.getEventsForDay(OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//
//        assertEquals(karmen, retrievedEvent.get(0));
//    }
//}
