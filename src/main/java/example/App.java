package example;

import example.model.Event;
import example.facade.BookingFacadeImpl;
import example.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.OffsetDateTime;

import java.time.*;

public class App 
{
    public static void main( String[] args ) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacadeImpl =
                (BookingFacadeImpl) applicationContext.getBean("bookingFacadeImpl");
        User stepan = new User(12345, "Stepan", "Stepan@email");
        User ostap = new User(333, "Ostap", "ostap@email");
        User ivan = new User(444, "Ivan", "ivan@email");
        bookingFacadeImpl.createUser(stepan);
        bookingFacadeImpl.createUser(ostap);
        bookingFacadeImpl.createUser(ivan);
        bookingFacadeImpl.deleteUser(12345);
        bookingFacadeImpl.updateUser(new User(333, "Sergii", "sergii@email"));
        User foundUser = bookingFacadeImpl.getUserById(125);
        System.out.println(foundUser.toString());

        Event Karmen = new Event(881, "Karmen",OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
        bookingFacadeImpl.createEvent(Karmen);
        Event foundEvent = bookingFacadeImpl.getEventById(669);
        System.out.println(foundEvent.toString());

    }
}
