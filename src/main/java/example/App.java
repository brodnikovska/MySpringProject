package example;

import example.facade.BookingFacadeImpl;
import example.model.Event;
import example.model.Ticket;
import example.model.User;
import example.model.UserAccount;
import example.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class App 
{
    public static void main( String[] args ) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacadeImpl =
                (BookingFacadeImpl) applicationContext.getBean("bookingFacadeImpl");

        User visitor = new User("John", "john40email");
        bookingFacadeImpl.createUser(visitor);
        UserAccount johnsAccount= new UserAccount(visitor, new BigDecimal("3000"));
        bookingFacadeImpl.createUserAccount(johnsAccount);
        visitor.setUserAccount(johnsAccount);
        bookingFacadeImpl.updateUser(visitor);

        Event show = new Event("Spartacus18", OffsetDateTime.of(LocalDateTime.of(2023, 4, 10, 19, 0), ZoneOffset.UTC), new BigDecimal("1500"));
        bookingFacadeImpl.createEvent(show);
        Ticket ticket = bookingFacadeImpl.bookTicket(visitor.getId(), show.getId(), 12, Ticket.Category.BAR);
        System.out.println(ticket.toString());
    }
}
