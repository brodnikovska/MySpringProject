package example;

import example.facade.BookingFacadeImpl;
import example.model.User;
import example.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args ) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacadeImpl =
                (BookingFacadeImpl) applicationContext.getBean("bookingFacadeImpl");
        User foundUser = bookingFacadeImpl.getUserById(1);
        System.out.println(foundUser.toString());

//        Event Karmen = new Event(881, "Karmen",OffsetDateTime.of(LocalDateTime.of(2023, 4, 4, 19, 0), ZoneOffset.UTC));
//        bookingFacadeImpl.createEvent(Karmen);
//        Event foundEvent = bookingFacadeImpl.getEventById(669);
//        System.out.println(foundEvent.toString());
//
//        bookingFacadeImpl.bookTicket(444, 881, 11, Ticket.Category.BAR);
//        bookingFacadeImpl.bookTicket(444, 667, 12, Ticket.Category.BAR);
//        List<Ticket> foundTicket = bookingFacadeImpl.getBookedTickets(ivan, 10, 10);
//        foundTicket.stream().map(Object::toString).forEach(System.out::println);
    }
}
