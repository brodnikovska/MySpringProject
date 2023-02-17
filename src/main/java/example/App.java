package example;

import example.model.User;
import example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App 
{
    public static void main( String[] args ) throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService =
                (UserService) applicationContext.getBean("userService");
        User foundUser = userService.getUserByEmail("semen@i.ua");
        System.out.println(foundUser.getName());
    }
}
