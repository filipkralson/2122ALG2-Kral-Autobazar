package com.filipkral.autobazar.app;

/**
 * Class of Auta object
 * 
 * @author filip.kral
 */
public class Auta {

    private String brand;
    private String model;
    private int yearOfManufacture;
    private String color;
    private int price;
    private double milage;
    private String vin;
    private String state;
    private String country;

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getVin() {
        return vin;
    }

    public double getMilage() {
        return milage;
    }

    public Auta(String brand, String model, String vin, double milage, int yearOfManufacture,
            String color, int price, String state, String country) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.milage = milage;
        this.yearOfManufacture = yearOfManufacture;
        this.state = state;
        this.country = country;
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
        this.yearOfManufacture = c.yearOfManufacture;
        this.vin = c.vin;
        this.milage = c.milage;
        this.state = c.state;
        this.country = c.country;
        this.color = c.color;
        this.price = c.price;
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
        return String.format("%-15s %-20s %-11.2f %-7d %-20s %-20s %-20s %-20s %-5d $\n", getBrand(), getModel(),
                getMilage(),
                getYearOfManufacture(), getVin(), getState(), getCountry(), getColor(), getPrice());
    }

}
