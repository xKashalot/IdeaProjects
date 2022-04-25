package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarService carService;

    List<Car> carList = new ArrayList<>(Arrays.asList(
            new Car("BMW", 2010, 200),
            new Car("Mazda", 2015, 150),
            new Car("Audi", 2000, 100),
            new Car("Volkswagen", 2020, 400),
            new Car("Toyota", 1990, 250)));

    @Override
    public List<Car> showCars(int count) {
        if (count == 0 || count > 5) {
            return carList;
        }
        return carList.stream().limit(count).collect(Collectors.toList());
    }
}
