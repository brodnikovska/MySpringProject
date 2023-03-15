package example.service.impl;

import example.dao.repository.EventRepository;
import example.model.Event;
import example.model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

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
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEvent() {
        Event turandot = new Event("Turandot", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC), new BigDecimal(200));

        when(eventRepository.save(any(Event.class))).thenReturn(turandot);

        Event newEvent = eventServiceImpl.createEvent(new Event());

        assertEquals(turandot, newEvent);
    }

    @Test
    public void testUpdateEvent() {
        Event turandot = new Event("Turandot", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC), new BigDecimal(200));

        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(turandot));
        when(eventRepository.save(any(Event.class))).thenReturn(turandot);

        Event updatedEvent = eventServiceImpl.updateEvent(new Event());

        assertEquals(turandot, updatedEvent);
    }

    @Test
    public void testDeleteEvent() {
        long userId = 1;
        doNothing().when(eventRepository).deleteById(anyLong());

        boolean isRemoved = eventServiceImpl.deleteEvent(userId);

        assertTrue(isRemoved);
        verify(eventRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testGetEventById() {
        long userId = 1;
        Event turandot = new Event("Turandot", OffsetDateTime.of(LocalDateTime.of(2023, 1, 1, 0, 0), ZoneOffset.UTC), new BigDecimal(200));

        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(turandot));

        Event retrievedEvent = eventServiceImpl.getEventById(userId);

        assertEquals(turandot, retrievedEvent);
    }

    @Test
    public void testGetEventsByTitle() {
        Event karmen = new Event("Karmen", OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC), new BigDecimal(200));

        when(eventRepository.findByTitle(anyString())).thenReturn(new ArrayList<>(List.of(karmen)));

        List<Event> retrievedEvent = eventServiceImpl.getEventsByTitle("Karmen", 10, 1);

        assertEquals(karmen, retrievedEvent.get(0));
    }

    @Test
    public void testGetEventsForDay() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC);
        Event karmen = new Event("Karmen", offsetDateTime, new BigDecimal(200));

        when(eventRepository.findByDate(any(OffsetDateTime.class))).thenReturn(new ArrayList<>(List.of(karmen)));

        List<Event> retrievedEvent = eventServiceImpl.getEventsForDay(offsetDateTime, 10, 1);

        assertEquals(karmen, retrievedEvent.get(0));
    }
    @Test
    public void testUpdateEventNotFound() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC);
        long eventId = 1;
        Event karmen = new Event("Karmen", offsetDateTime, new BigDecimal(200));
        karmen.setId(eventId);

        when(eventRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            eventServiceImpl.updateEvent(karmen);
        } catch (JpaObjectRetrievalFailureException e) {
            assertEquals("Event with id 1 does not exist", e.getMessage());
        }
    }
}
