package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import utils.ExceptionFileNotFound;
import utils.ExceptionInputOutput;

public class Autobazar {

    private Random random;
    private String name, s;
    private String[] split;
    private File file;
    private ArrayList<Prodejci> sellers;
    private ArrayList<Auta> cars;

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

    public String getSpecificCar(int specificCar) {
        StringBuilder s = new StringBuilder();

        s.append(cars.get(specificCar - 1));

        return s.toString();
    }

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

    public String printSellers() {
        StringBuilder s = new StringBuilder();
        int count = 0;

        for (Prodejci seller : sellers) {
            s.append((count += 1) + ". ").append(seller);
        }

        return s.toString();
    }

    public void writeCars() throws ExceptionFileNotFound, ExceptionInputOutput {

        // změnit cestu k souboru, nainicializovat proměnné jako private

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
            e.printStackTrace(); // udělat vlastní exception
        }
    }

    public String printCars() {
        StringBuilder s = new StringBuilder();

        for (Auta car : cars) {
            s.append(car);
        }

        return s.toString();
    }

    public Auta getRandomCar() {

        random = new Random();

        return cars.get(random.nextInt(cars.size()));

    }

    public double pickRandomPercent(int max, int min) {
        random = new Random();
        return 100 - random.nextInt(max - min) + min;
    }

    public Prodejci getSpecificSeller(int specificSellerNumber) {
        return sellers.get(specificSellerNumber - 1);
    }

    public void sellTime(Prodejci seller) {

        //přidávání peněz na účet prodejce a majitele

        double priceModif;
        if (seller.getExp() <= 10 && seller.getExp() >= 8) {
            priceModif = getRandomCar().getPrice() * (pickRandomPercent(5, 1) / 100);
            seller.setMoney(priceModif * 0.1);
        } else if (seller.getExp() <= 7 && seller.getExp() >= 4) {
            priceModif = getRandomCar().getPrice() * (pickRandomPercent(10, 6) / 100);
            seller.setMoney(priceModif * 0.1);
        } else {
            priceModif = getRandomCar().getPrice() * (pickRandomPercent(15, 11) / 100);
            seller.setMoney(priceModif * 0.1);
        }
    }

    public static void main(String[] args) throws ExceptionFileNotFound, ExceptionInputOutput {
        Autobazar abc = new Autobazar("ABC");
        abc.addSeller(new Prodejci("Josef", "Krátký", 30, 10));
        abc.addSeller(new Prodejci("Arnošta", "z Pardubic", 55, 8));
        abc.writeCars();
        System.out.println(abc);
        ui.AutobazarApp.displaySellersHead();
        System.out.println(abc.printSellers());
        ui.AutobazarApp.displayCarsHead();
        System.out.println(abc.printCars());
        abc.sellTime(abc.getSpecificSeller(1));
        System.out.println(abc.getRandomCar());
        System.out.println(abc.getSpecificSeller(1).getMoney());
        System.out.println(abc.pickRandomPercent(5, 1) / 100);
    }
}
