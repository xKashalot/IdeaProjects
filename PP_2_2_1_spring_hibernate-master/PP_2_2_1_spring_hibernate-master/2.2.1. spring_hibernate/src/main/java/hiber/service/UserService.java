package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User findByCar(String carModel, int carSeries);
    //User findByCar2(Car car);
}
