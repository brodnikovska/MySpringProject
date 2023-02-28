package example;

import example.facade.BookingFacadeTest;
import example.service.EventServiceTest;
import example.service.TicketServiceTest;
import example.service.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ UserServiceTest.class, EventServiceTest.class, TicketServiceTest.class,
        BookingFacadeTest.class })
public class TestSuite {
}
