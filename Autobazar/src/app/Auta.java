package app;

public class Auta implements Comparable<Auta> {

    private String brand;
    private String model;
    private double engineCapacity;
    private int kilowatts;
    private int kilometers;
    private int yearOfManufacture;
    private String fuel;
    private String color;
    private int price;

    public Auta(String brand, String model, double engineCapacity, int kilowatts, int kilometers, int yearOfManufacture,
            String fuel, String color, int price) {
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.kilowatts = kilowatts;
        this.kilometers = kilometers;
        this.yearOfManufacture = yearOfManufacture;
        this.fuel = fuel;
        this.color = color;
        this.price = price;
    }

    @Override
    public int compareTo(Auta o) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getFuel() {
        return fuel;
    }

    public int getKilowatts() {
        return kilowatts;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public int getKilometers() {
        return kilometers;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-7.1f %-7d %-15d %-15d %-15s %-20s %-15d\n", getBrand(), getModel(), getEngineCapacity(),
                getKilowatts(), getKilometers(), getYearOfManufacture(), getFuel(), getColor(), getPrice());
    }

}
