package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User bob = new User();
        bob.setEmail("bob@gmail.com");
        bob.setPassword("12345678");
        userService.add(bob);

        User alice = new User();
        alice.setEmail("alice@gmail.com");
        alice.setPassword("12343210");
        userService.add(alice);

        userService.listUsers().forEach(System.out::println);
    }
}
