package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car(1987, "Toyota");
        Car car2 = new Car(2002, "Lexus");
        Car car3 = new Car(1999, "Subaru");
        Car car4 = new Car(2000, "Opel");

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());

        }
        List<User> usersWithCar = userService.findUserByCar("Toyota", 1987);
        if (usersWithCar.isEmpty()) {
            System.out.println("Никто не владеет этой машиной");
        } else {
            for (User user : usersWithCar) {
                System.out.println(user.getFirstName() + " " + user.getLastName() +
                        " владеет " + user.getCar().getModel() + " " + user.getCar().getSeries());
            }
        }

        context.close();
    }
}
