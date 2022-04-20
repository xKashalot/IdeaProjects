package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CarService {
    List<Car> carList = new ArrayList<>(Arrays.asList(
            new Car("BMW", 2010, 200),
            new Car("Mazda", 2015, 150),
            new Car("Audi", 2000, 100),
            new Car("Volkswagen", 2020, 400),
            new Car("Toyota", 1990, 250)));

    List<Car> showCars(int count);

//     static List<Car> carList() {
//        List<Car> carList = new ArrayList<>();
//        carList.add(new Car("BMW", 2010, 200));
//        carList.add(new Car("Mazda", 2015, 150));
//        carList.add(new Car("Audi", 2000, 100));
//        carList.add(new Car("Volkswagen", 2020, 400));
//        carList.add(new Car("Toyota", 1990, 250));
//        return carList;
//    }
//
//    static List<Car> cars(List<Car> carList, int count){
//        if (count == 0 || count > 5) {
//            return carList;
//        }
//        return carList.stream().limit(count).collect(Collectors.toList());
//    }
}
