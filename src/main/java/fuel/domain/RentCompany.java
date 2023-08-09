package fuel.domain;

import static java.util.stream.Collectors.joining;

import fuel.domain.car.Car;
import java.util.ArrayList;
import java.util.List;

public final class RentCompany {

    private final List<Car> cars;

    private RentCompany(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    private RentCompany() {
        this.cars = new ArrayList<>();
    }

    public static RentCompany create(List<Car> cars) {
        return new RentCompany(cars);
    }

    public static RentCompany create() {
        return new RentCompany();
    }

    public boolean addCar(Car car) {
        return cars.add(car);
    }

    public String generateReport() {
        return cars.stream()
                .map(Car::generateReport)
                .collect(joining());
    }
}
