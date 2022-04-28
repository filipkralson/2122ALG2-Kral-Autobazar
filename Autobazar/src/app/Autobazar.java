package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import utils.ExceptionFileNotFound;
import utils.ExceptionInputOutput;

public class Autobazar {

    private String name, s;
    private String[] split;
    private File file;
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

    public void addSeller(Prodejci seller) { // exception, aby nebylo možné zadat stejné prodejce
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
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

    public String printSellers() {
        StringBuilder s = new StringBuilder();

        for (Prodejci seller : sellers) {
            s.append(seller);
        }

        return s.toString();
    }

    public void writeCars() throws ExceptionFileNotFound, ExceptionInputOutput {

        System.setProperty("file.encoding", "UTF-8");
        file = new File("/Users/filip/Documents/ALG2-SemestralProject/Autobazar/src/cars.csv");
        s = "";
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while ((s = reader.readLine()) != null) {
                    split = s.split(";");
                    String brand = split[0];
                    String model = split[1];
                    String engineCapacity = split[2];
                    double dblEngine = Double.parseDouble(engineCapacity);
                    String kW = split[3];
                    int dblKW = Integer.parseInt(kW);
                    String km = split[4];
                    int intKm = Integer.parseInt(km);
                    String year = split[5];
                    int intYear = Integer.parseInt(year);
                    String fuel = split[6];
                    String color = split[7];
                    String price = split[8];
                    int intPrice = Integer.parseInt(price);

                    cars.add(new Auta(brand, model, dblEngine, dblKW, intKm, intYear, fuel, color, intPrice));
                }

            } catch (FileNotFoundException e) {
                throw new ExceptionFileNotFound("Zadaný soubor nebyl nalezen!");
            } catch (IOException e) {
                throw new ExceptionInputOutput("Chyba při vstupu!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();    //udělat vlastní exception
        }
    }

    public String printCars() {
        StringBuilder s = new StringBuilder();

        for (Auta car : cars) {
            s.append(car);
        }

        return s.toString();
    }

    public static void main(String[] args) throws ExceptionFileNotFound, ExceptionInputOutput {
        Autobazar abc = new Autobazar("ABC");
        abc.addSeller(new Prodejci("Josef", "Krátký", 30, 10));
        abc.addSeller(new Prodejci("Arnošta", "z Pardubic", 55, 8));
        //abc.addCar(new Auta("Škoda", "Fabia", 1.4, 55, 230000, 2003, "benzín", "stříbrná metalíza", 50000));

        System.out.println(abc);
        System.out.println(abc.printSellers());
        System.out.format("%-15s %-15s %-7s %-7s %-15s %-15s %-15s %-20s %-15s\n\n", "Značka", "Model", "Objem",
                "KW", "Kilometry", "Rok Výroby", "Palivo", "Barva", "Cena");
        abc.writeCars();
        System.out.println(abc.printCars());
    }
}
