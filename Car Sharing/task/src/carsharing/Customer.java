package carsharing;

public class Customer {
    private final int id;
    private final String name;

    Car rentedCar;

    public Customer(int id, String name, Car rentedCar) {
        this.id = id;
        this.name = name;
        this.rentedCar = rentedCar;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }
}
