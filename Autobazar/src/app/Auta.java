package app;

public class Auta implements Comparable<Auta> {

    private String brand, model;
    private double engineCapacity;
    private int kilometers, yearOfManufacture, price;
    private String color;

    @Override
    public int compareTo(Auta o) {
        // TODO Auto-generated method stub
        return 0;
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

    //vkladání aut ze souboru .csv do objektů 


    
}
