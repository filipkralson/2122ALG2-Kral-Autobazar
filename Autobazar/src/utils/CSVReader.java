package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import app.Auta;

import java.util.Locale;

public class CSVReader {

    static Locale czech = new Locale("cs", "CZ");

    public static void main(String[] args) throws ExceptionFileNotFound, ExceptionInputOutput {
        File file = new File("/Users/filip/Documents/ALG2-SemestralProject/Autobazar/src/cars.csv");
        String s = ("");
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                System.out.format("%-15s %-15s %-7s %-7s %-15s %-15s %-15s %-20s %-15s\n", "Značka", "Model", "Objem",
                        "KW", "Kilometry", "Rok Výroby", "Palivo", "Barva", "Cena");
                while ((s = reader.readLine()) != null) {

                    String[] split = s.split(";");
                    String brand = split[0];
                    String model = split[1];
                    String engine = split[2];
                    double dblEngine = Double.parseDouble(engine);
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

                    Auta cars = new Auta(brand, model, dblEngine, dblKW, intKm, intYear, fuel, color, intPrice);

                    System.out.println(cars);
                }

            } catch (FileNotFoundException e) {
                throw new ExceptionFileNotFound("Zadaný soubor nebyl nalezen!");
            } catch (IOException e) {
                throw new ExceptionInputOutput("Chyba při vstupu!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
