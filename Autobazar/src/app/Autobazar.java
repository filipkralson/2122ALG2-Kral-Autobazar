package app;

import java.util.ArrayList;

public class Autobazar {

    private String name;
    static ArrayList<Prodejci> sellers;
    static ArrayList<Auta> cars;

    public Autobazar(String name) {
        this.name = name;
        cars = new ArrayList<>();
        sellers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addSeller(Prodejci seller) {    //exception, aby nebylo možné zadat stejné prodejce
        sellers.add(seller);
    }

    public void addCar(Auta car) {
        cars.add(car);
    }

    public int getSellersCount() {
        return sellers.size();
    }

    public int getCarsCount() {
        return cars.size();
    }

    public double bankAccount() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n",getName(),getCarsCount(),getSellersCount());
    }

    public String printCars() {
        StringBuilder s = new StringBuilder();

        for(Auta car : cars) {
            s.append(car);
        }

        return s.toString();
    }

    public String printSellers() {
        StringBuilder s = new StringBuilder();

        for(Prodejci seller : sellers) {
            s.append(seller);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        Autobazar abc = new Autobazar("ABC");
        abc.addSeller(new Prodejci("Josef", "Krátký", 30, 10));
        abc.addSeller(new Prodejci("Arnošta", "z Pardubic", 55, 8));
        

        System.out.println(abc);
        System.out.println(abc.printSellers());
        System.out.println(abc.printCars());

    }
}
