package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Tuple;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",
              new Car("Jaguar", 1982)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",
              new Car("Toyota", 2024)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",
              new Car("BMW", 2025)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());

         Car car = user.getCar();
         if (car != null) {
            System.out.println("Car.Id = " + car.getId());
            System.out.println("Car.Model = " + car.getModel());
            System.out.println("Car.Series = " + car.getSeries());
         } else {
            System.out.println("There is no car");
         }

         System.out.println();
      }

      /// Ицем пользователей в БД по автомобилю
      class Tuple<X, Y> {
         public final X x;
         public final Y y;
         public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
         }
      }

      List<Tuple<String, Integer>> cars = new ArrayList<>();
      cars.add(new Tuple<>("Toyota", 2024));
      cars.add(new Tuple<>("BMW", 2025));
      cars.add(new Tuple<>("Jaguar", 2025));
      cars.add(new Tuple<>("BYD", 2035));

     for (Tuple<String, Integer> car : cars) {
        User carUser = userService.getUserByCar(car.x, car.y);
        if (carUser != null) {
           System.out.println("User found (" + carUser.getFirstName() + " " + carUser.getLastName() +
                   ") by car: model = " + car.x + " and series = " + car.y);
        } else {
           System.out.println("There is no user by car : model = " + car.x + ", series = " + car.y);
        }
     }

      context.close();
   }
}
