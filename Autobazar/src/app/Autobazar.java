package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;

import utils.ExceptionFileNotFound;
import utils.ExceptionInputOutput;
import utils.ExceptionNoMoreSale;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class Autobazar {

    public static final Collator col = Collator.getInstance(new Locale("cs", "CZ"));
    public static final String IMPACT_FONT = "./Autobazar/fonts/impact.ttf";
    public static final String LIBERATIONSANS_REGULAR = "./Autobazar/fonts/LiberationSans-Regular.ttf";
    public static final String LIBERATIONSANS_BOLD = "./Autobazar/fonts/LiberationSans-Bold.ttf";
    public static final Comparator<Auta> COMP_BY_BRAND = (Auta c1, Auta c2) -> {
        int value = col.compare(c1.getBrand(), c2.getBrand());
        if (value == 0) {
            value = col.compare(c1.getModel(), c2.getModel());
        }
        return value;
    };

    public static final Comparator<Prodejci> COMP_BY_EXP = (Prodejci p1, Prodejci p2) -> {
        return p2.getExp() - p1.getExp();
    };

    private Random random;
    private String name, s;
    private String[] split;
    private List<Prodejci> sellers;
    private List<Auta> cars;

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

        s.append(getCarsSortedByBrand().get(specificCar - 1));

        return s.toString();
    }

    public String printSellers() { // přispůsobit pro použití do sellersSortByExpToString()
        StringBuilder s = new StringBuilder();
        int count = 0;

        ui.AutobazarApp.displaySellersHead();
        for (Prodejci seller : sellers) {
            s.append((count += 1) + ". ").append(seller);
        }

        return s.toString();
    }

    public void loadCars(File file) throws ExceptionFileNotFound, ExceptionInputOutput {

        // čárku na tečku

        System.setProperty("file.encoding", "UTF-8");
        s = "";
        file = new File("./Autobazar/src/cars.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String brand, model, engineCapacity, kW, km, year, fuel, color, price;
            double dblEngine;
            int dblKW, intKm, intYear, intPrice;

            while ((s = reader.readLine()) != null) {
                split = s.split(";");

                brand = split[0];
                model = split[1];
                engineCapacity = split[2];
                dblEngine = Double.parseDouble(engineCapacity);
                kW = split[3];
                dblKW = Integer.parseInt(kW);
                km = split[4];
                intKm = Integer.parseInt(km);
                year = split[5];
                intYear = Integer.parseInt(year);
                fuel = split[6];
                color = split[7];
                price = split[8];
                intPrice = Integer.parseInt(price);

                cars.add(new Auta(brand, model, dblEngine, dblKW, intKm, intYear, fuel, color, intPrice));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new ExceptionFileNotFound("Zadaný soubor nebyl nalezen!");
        } catch (IOException e) {
            throw new ExceptionInputOutput("Chyba při vstupu!");
        }
    }

    public void saveToFile(/* File results */) throws ExceptionInputOutput, IOException {

        // ukládat seznam prodejců s profitem, ukládat počet prodaných aut a jejich
        // celková cena, uložit čistý profit (všechno přes StringBuiler)

        PdfDocument pdfDoc = new PdfDocument(
                new PdfWriter("C:/Users/filip/Documents/ALG2-SemestralProject/Autobazar/src/data"));
        Document doc = new Document(pdfDoc);
        FontProgram impact = FontProgramFactory.createFont(IMPACT_FONT);
        FontProgram liberation = FontProgramFactory.createFont(LIBERATIONSANS_REGULAR);
        PdfFont impactFont = PdfFontFactory.createFont(impact, PdfEncodings.UTF8);
        PdfFont liberationFont = PdfFontFactory.createFont(liberation, PdfEncodings.UTF8);
        String headder = "TÝDENNÍ VYÚČTOVÁNÍ";

        Text top = new Text(headder);
        top.setFontColor(ColorConstants.BLACK);
        top.setFont(impactFont);
        Paragraph para1 = new Paragraph(top);

        Text inner = new Text(printSellers());
        inner.setFontColor(ColorConstants.BLACK);
        inner.setFont(liberationFont);
        Paragraph para2 = new Paragraph(inner);

        doc.add(para1);
        doc.add(para2);

        doc.close();
    }

    public String printCars() {
        StringBuilder s = new StringBuilder();
        int count = 0;
        ui.AutobazarApp.displayCarsHead();
        for (Auta car : cars) {
            if (count < 9) {
                s.append(count += 1).append(".  ").append(car);
            } else {
                s.append(count += 1).append(". ").append(car);
            }

        }

        return s.toString();
    }

    public void sellersSortByExp() {
        Collections.sort(sellers, COMP_BY_EXP);
    }

    public void carsSortByBrand() {
        Collections.sort(cars, COMP_BY_BRAND);
    }

    // deep copy
    public ArrayList<Auta> getCars() {
        ArrayList<Auta> copy = new ArrayList<>();
        for (Auta car : cars) {
            copy.add(new Auta(car));
        }
        return copy;
    }

    public ArrayList<Prodejci> getSellers() {
        ArrayList<Prodejci> copy = new ArrayList<>();
        for (Prodejci seller : sellers) {
            copy.add(new Prodejci(seller));
        }
        return copy;
    }

    public ArrayList<Prodejci> getSellersSortedByExp() {
        sellersSortByExp();
        return getSellers();
    }

    /**
     * Metoda pro dostání ArrayListu seřazených aut pro další práci
     * 
     * @return getCars();
     */
    public ArrayList<Auta> getCarsSortedByBrand() {
        carsSortByBrand();
        return getCars();
    }

    public Auta getRandomCar() {

        random = new Random();

        return getCarsSortedByBrand().get(random.nextInt(getCarsSortedByBrand().size()));

    }

    public double pickRandomPercent(int max, int min) {
        random = new Random();
        return 100 - random.nextInt(max - min) + min;
    }

    public Prodejci getSpecificSeller(int specificSellerNumber) {
        return getSellersSortedByExp().get(specificSellerNumber - 1);
    }

    public void sellTime(Prodejci seller) throws ExceptionNoMoreSale {
        Auta car = new Auta(getRandomCar());
        double priceModif;

        if (seller.getCarSale() >= 3) {
            throw new ExceptionNoMoreSale("Prodejce již nemůže prodávat!");
        } else {
            if (seller.getExp() <= 10 && seller.getExp() >= 8) {
                // System.out.println(car.getPrice());
                priceModif = car.getPrice() * (pickRandomPercent(5, 0) / 100);
                seller.commission(priceModif * 0.1);
                seller.setCarSale(seller.getCarSale() + 1);
                // System.out.println(seller.getMoney());
                // System.out.println(seller.getCarSale());
                // cars.remove(car);
            } else if (seller.getExp() <= 7 && seller.getExp() >= 4) {
                priceModif = car.getPrice() * (pickRandomPercent(10, 6) / 100);
                seller.commission(priceModif * 0.1);
                seller.setCarSale(seller.getCarSale() + 1);
                // cars.remove(car);
            } else {
                priceModif = car.getPrice() * (pickRandomPercent(15, 11) / 100);
                seller.commission(priceModif * 0.1);
                seller.setCarSale(seller.getCarSale() + 1);
                // cars.remove(car);
            }
        }
    }

    // udělat main

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

    public static void main(String[] args) throws ExceptionInputOutput, ExceptionNoMoreSale, IOException {

        File file = new File("./Autobazar/src/cars.csv");
        Autobazar abc = new Autobazar("ABC");
        abc.addSeller(new Prodejci("Josef", "Krátký", 30, 4));
        abc.addSeller(new Prodejci("Arnošta", "z Pardubic", 55, 8));
        abc.addSeller(new Prodejci("Jirkos", "Man", 47, 5));
        abc.addSeller(new Prodejci("Dežo", "Man", 31, 10)); // exception na zadání vyššího jak 10.lvl
        abc.loadCars(file);
        System.out.println(abc);
        abc.sellersSortByExp();
        System.out.println(abc.printSellers());
        abc.carsSortByBrand();
        System.out.println(abc.printCars());
        abc.saveToFile();
        abc.sellTime(abc.getSpecificSeller(2));
        System.out.println(abc.getSpecificSeller(2).getExp());
        

    }
}
