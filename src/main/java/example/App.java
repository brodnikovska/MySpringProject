package example;

import example.model.User;
import example.service.BookingService;
import example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingService bookingService =
                (BookingService) applicationContext.getBean("bookingService");
        User foundUser = bookingService.getUserById(12345);
        System.out.println(foundUser.getId() + " " + foundUser.getName());
    }
}
