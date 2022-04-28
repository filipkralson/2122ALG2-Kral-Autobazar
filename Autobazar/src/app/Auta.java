package app;

public class Auta implements Comparable<Auta> {

    private String brand;
    private String model;
    private String color;
    private String fuel;
    private double engineCapacity;
    private int kilometers;
    private int yearOfManufacture;
    private int price;
    private int kilowatts;

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

}
