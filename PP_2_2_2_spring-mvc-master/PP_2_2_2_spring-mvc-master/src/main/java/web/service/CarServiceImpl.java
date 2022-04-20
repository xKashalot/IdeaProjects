package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarService carService;

    @Override
    public List<Car> showCars(int count) {
        if (count == 0 || count > 5) {
            return carService.carList;
        }
        return carService.carList.stream().limit(count).collect(Collectors.toList());
    }
}
