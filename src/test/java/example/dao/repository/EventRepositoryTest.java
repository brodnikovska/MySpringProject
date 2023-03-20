package example.dao.repository;

import example.model.Event;
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
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    private static final OffsetDateTime OFFSET_DATE_TIME = OffsetDateTime.now();
    private static final BigDecimal TICKET_PRICE = new BigDecimal(200);

    @Test
    public void testFindByTitle() {
        Event ballet = new Event();
        ballet.setTitle("Ballet A");
        ballet.setDate(OFFSET_DATE_TIME);
        ballet.setTicketPrice(TICKET_PRICE);
        eventRepository.save(ballet);

        Event foundEvent = eventRepository.findByTitle("Ballet A").get(0);

        assertEquals("Ballet A", foundEvent.getTitle());
    }

    @Test
    public void testFindByDate() {
        Event ballet = new Event();
        ballet.setTitle("Ballet D");
        ballet.setDate(OFFSET_DATE_TIME);
        ballet.setTicketPrice(TICKET_PRICE);
        eventRepository.save(ballet);

        Event foundEvent = eventRepository.findByDate(OFFSET_DATE_TIME).get(0);

        assertEquals(OFFSET_DATE_TIME, foundEvent.getDate());
    }
}
