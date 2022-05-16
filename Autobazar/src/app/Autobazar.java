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

import utils.AutobazarInterface;
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

public class Autobazar implements AutobazarInterface{

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
    private double money;

    /**
     * Constructor
     * @param name
     */
    public Autobazar(String name) {
        this.name = name;
        cars = new ArrayList<>();
        sellers = new ArrayList<>();
    }


    /**
     * Method for getting money
     * 
     * @return double
     */
    public double getMoney() {
        return money;
    }

    /**
     * Method for getting a name
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting bazaar
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for adding a seller
     * 
     * @param seller
     */
    public void addSeller(Prodejci seller) { // exception, aby nebylo možné zadat stejné prodejce
        sellers.add(seller);
    }

    /**
     * Method for adding a car
     * 
     * @param car
     */
    public void addCar(Auta car) {
        cars.add(car);
    }

    /**
     * Method for getting sellers count
     * 
     * @return int
     */
    public int getSellersCount() {
        return sellers.size();
    }

    /**
     * Method for getting count of cars
     * 
     * @return int
     */
    public int getCarsCount() {
        return cars.size();
    }

    /**
     * Method for saving money
     * 
     * @return double
     */
    public double bankAccount(double value) {
        return this.money = value + this.money;     //možné použití trapného interface pro 1 metodu
    }

    /**
     * Method for getting a specific car
     * 
     * @param specificCar
     * @return Object Auta
     */
    public Auta getSpecificCar(int specificCar) {
        return getCarsSortedByBrand().get(specificCar-1);
    }

    /**
     * Method for printing sellers
     * 
     * @return String s
     */
    public String printSellers() {
        StringBuilder s = new StringBuilder();
        int count = 0;

        ui.AutobazarApp.displaySellersHead();
        for (Prodejci seller : sellers) {
            s.append((count += 1) + ". ").append(seller);
        }

        return s.toString();
    }

    /**
     * Method for loading list of cars and saving them into objects
     * 
     * @param file
     * @throws ExceptionFileNotFound
     * @throws ExceptionInputOutput
     */
    public void loadCars(File file) throws ExceptionFileNotFound, ExceptionInputOutput {

        // čárku na tečku

        System.setProperty("file.encoding", "UTF-8");
        s = "";
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

    /**
     * Method for saving week results into .pdf, use of itextpdf library
     * 
     * @throws ExceptionInputOutput
     * @throws IOException
     */
    public void saveToFile(/* File results */) throws ExceptionInputOutput, IOException {

        // ukládat seznam prodejců s profitem, ukládat počet prodaných aut a jejich
        // celková cena, uložit profit bazaru, uložit čistý profit (všechno přes StringBuiler)
        // následné čtení z vygenerovaného pdf (nebo txt), zapisování do konzole

        PdfDocument pdfDoc = new PdfDocument(
                new PdfWriter("C:/Users/filip/Documents/ALG2-SemestralProject/Autobazar/src/data/results.pdf"));
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

    /**
     * Method for printing list of cars
     * 
     * @return String s
     */
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

    /**
     * Method for sorting list of sellers with specific comparator
     */
    public void sellersSortByExp() {
        Collections.sort(sellers, COMP_BY_EXP);
    }

    /**
     * Method for sorting list of cars with specific comparator
     */
    public void carsSortByBrand() {
        Collections.sort(cars, COMP_BY_BRAND);
    }

    /**
     * Method for deep copy of cars list
     * 
     * @return ArrayList copy
     */
    public ArrayList<Auta> getCars() {
        ArrayList<Auta> copy = new ArrayList<>();
        for (Auta car : cars) {
            copy.add(new Auta(car));
        }
        return copy;
    }

    /**
     * Method for deep copy of sellers list
     * 
     * @return ArrayList copy
     */
    public ArrayList<Prodejci> getSellers() {
        ArrayList<Prodejci> copy = new ArrayList<>();
        for (Prodejci seller : sellers) {
            copy.add(new Prodejci(seller));
        }
        return copy;
    }

    /**
     * Method for getting sorted arraylist of sellers
     * 
     * @return getSellers()
     */
    public ArrayList<Prodejci> getSellersSortedByExp() {
        sellersSortByExp();
        return getSellers();
    }

    /**
     * Method for getting sorted arraylist of cars
     * 
     * @return getCars()
     */
    public ArrayList<Auta> getCarsSortedByBrand() {
        carsSortByBrand();
        return getCars();
    }

    /**
     * Method for getting a random car
     * 
     * @return Object Auta
     */
    public Auta getRandomCar() {

        random = new Random();

        return getCarsSortedByBrand().get(random.nextInt(getCarsSortedByBrand().size()));

    }

    /**
     * Method for picking random percent from a range
     * 
     * @param max
     * @param min
     * @return double value
     */
    public double pickRandomPercent(int max, int min) {
        random = new Random();
        return 100 - random.nextInt(max - min) + min;
    }

    /**
     * Method for getting specific seller
     * 
     * @param specificSellerNumber
     * @return Object Prodejci
     */
    public Prodejci getSpecificSeller(int specificSellerNumber) {
        return getSellersSortedByExp().get(specificSellerNumber - 1);
    }

    /**
     * Method for making a sale
     * 
     * @param seller
     * @throws ExceptionNoMoreSale
     */
    public void sellTime(Prodejci seller) throws ExceptionNoMoreSale {
        Auta car = new Auta(getRandomCar());
        double priceModif;
        double sellerCommision;

        if (seller.getCarSale() >= 3) {
            throw new ExceptionNoMoreSale("Prodejce již nemůže prodávat!");
        } else {
            if (seller.getExp() <= 10 && seller.getExp() >= 8) {
                //java.time, čas prodeje auta (random od 9:00 do 17:00), zapisovat do konzole 
                System.out.println(car.getPrice());
                priceModif = car.getPrice() * (pickRandomPercent(5, 0) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                bankAccount(priceModif-sellerCommision);
                seller.setCarSale(seller.getCarSale() + 1);
                System.out.println(seller.getMoney());
                System.out.println(seller.getCarSale());
                //cars.remove(car);
            } else if (seller.getExp() <= 7 && seller.getExp() >= 4) {
                priceModif = car.getPrice() * (pickRandomPercent(10, 6) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                bankAccount(priceModif-sellerCommision);
                seller.setCarSale(seller.getCarSale() + 1);
                // cars.remove(car);
            } else {
                priceModif = car.getPrice() * (pickRandomPercent(15, 11) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                bankAccount(priceModif-sellerCommision);
                seller.setCarSale(seller.getCarSale() + 1);
                // cars.remove(car);
            }
        }
    }

    // udělat main
    // zprovoznit pdf ukládání

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

    public static void main(String[] args) throws ExceptionInputOutput, ExceptionNoMoreSale, IOException {

        File file = new File("C:/Users/filip/Documents/ALG2-SemestralProject/Autobazar/src/cars.csv");
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
        abc.sellTime(abc.getSpecificSeller(2));
        System.out.println(abc.getSpecificSeller(2).getMoney());
        System.out.println(abc.getMoney());
        //abc.saveToFile();
        //System.out.println(abc.getSpecificCar(8));

    }
}
