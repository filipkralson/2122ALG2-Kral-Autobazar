package app;

/**
 * Class of Auta object
 * 
 * @author filip.kral
 */
public class Auta {

    private String brand;
    private String model;
    private double engineCapacity;
    private int kilowatts;
    private int kilometers;
    private int yearOfManufacture;
    private String fuel;
    private String color;
    private int price;

    /**
     * Contructor
     * 
     * @param brand
     * @param model
     * @param engineCapacity
     * @param kilowatts
     * @param kilometers
     * @param yearOfManufacture
     * @param fuel
     * @param color
     * @param price
     */
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

    /**
     * Defensive constructor
     * 
     * @param c
     */
    public Auta(Auta c) {
        this.brand = c.brand;
        this.model = c.model;
        this.engineCapacity = c.engineCapacity;
        this.kilowatts = c.kilowatts;
        this.kilometers = c.kilometers;
        this.yearOfManufacture = c.yearOfManufacture;
        this.fuel = c.fuel;
        this.color = c.color;
        this.price = c.price;
    }

    /**
     * Method for getting fuel of a each car
     * 
     * @return fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * Method for getting kilowatts of a each car
     * 
     * @return kilowatts
     */
    public int getKilowatts() {
        return kilowatts;
    }

    /**
     * Method for getting price of a each car
     * 
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method for getting color of a each car
     * 
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Method for getting yearOfManufacture of a each car
     * 
     * @return yearOfManufacture
     */
    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    /**
     * Method for getting kilometers of a each car
     * 
     * @return kilometers
     */
    public int getKilometers() {
        return kilometers;
    }

    /**
     * Method for getting engineCapacity of a each car
     * 
     * @return engineCapacity
     */    
    public double getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * Method for getting model of a each car
     * 
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Method for getting brand of a each car
     * 
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-7.1f %-7d %-15d %-15d %-15s %-20s %-15d\n", getBrand(), getModel(), getEngineCapacity(),
                getKilowatts(), getKilometers(), getYearOfManufacture(), getFuel(), getColor(), getPrice());
    }

}
