package web.service;

import web.model.Car;

import java.util.List;
import java.util.stream.Collectors;

public interface CarService {
    static List<Car> carList(List<Car> cars, int count){
        if (count == 0 || count > 5) {
            return cars;
        }
        return cars.stream().limit(count).collect(Collectors.toList());
    }
}
