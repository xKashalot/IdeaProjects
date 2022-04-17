package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printCars(@RequestParam(value = "count", defaultValue = "5") int allCars,
                            Model model){
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("BMW", 2010, 200));
        cars.add(new Car("Mazda", 2015, 150));
        cars.add(new Car("Audi", 2000, 100));
        cars.add(new Car("Volkswagen", 2020, 400));
        cars.add(new Car("Toyota", 1990, 250));
        cars = CarService.carList(cars, allCars);
        model.addAttribute("cars", cars);
        return "/cars";
    }
}
