package example;

import example.dao.UserDao;
import example.model.User;
import example.facade.BookingFacadeImpl;
import example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacadeImpl =
                (BookingFacadeImpl) applicationContext.getBean("bookingFacadeImpl");
        User user = new User(12345, "Stepan", "Stepan@email");
        bookingFacadeImpl.createUser(user);
        User foundUser = bookingFacadeImpl.getUserById(12345);
        System.out.println(foundUser.getId() + " " + foundUser.getName());
    }
}
