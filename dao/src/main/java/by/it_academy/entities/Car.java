package by.it_academy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car {
    private long carId;
    private String carName;
    private double pricePerDay;
    private boolean active;
    private String image;

    public Car(String carName, Double pricePerDay, String image) {
        this.carName = carName;
        this.pricePerDay = pricePerDay;
        this.image = image;
        this.active = true;
    }

    public Car(long carId, String carName, double pricePerDay, boolean active, String image) {
        this.carId = carId;
        this.carName = carName;
        this.pricePerDay = pricePerDay;
        this.active = active;
        this.image = image;
    }
}
