package example;

import example.model.User;
import example.facade.BookingFacadeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args ) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacadeImpl =
                (BookingFacadeImpl) applicationContext.getBean("bookingFacadeImpl");
        User stepan = new User(12345, "Stepan", "Stepan@email");
        User ostap = new User(333, "Ostap", "ostap@email");
        User ivan = new User(444, "Ivan", "KatRin@i.ua");
        bookingFacadeImpl.createUser(stepan);
        bookingFacadeImpl.createUser(ostap);
        bookingFacadeImpl.createUser(ivan);
        bookingFacadeImpl.deleteUser(12345);
        bookingFacadeImpl.updateUser(new User(333, "Sergii", "sergii@email"));
        User foundUser = bookingFacadeImpl.getUserById(125);
        System.out.println(foundUser.toString());
    }
}
