package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;

import utils.AutobazarInterface;
import utils.ExceptionFileNotFound;
import utils.ExceptionInputOutput;
import utils.ExceptionNoMoreSale;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Autobazar implements AutobazarInterface {

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
        if((p2.getExp() - p1.getExp())==0) {
            return p2.getAge() - p1.getAge();
        }
        return p2.getExp()-p1.getExp();
    };

    public static final File fileSellers = new File("../Autobazar/src/sellers.csv");
    public static final File fileCars = new File("../Autobazar/src/cars.csv");
    public static final File fileResultsPdf = new File("../Autobazar/src/data/results.pdf");
    public static final File fileResultsBinary = new File("../Autobazar/src/data/results.dat");


    private Random random;
    private String name, s;
    private String[] split;
    private List<Prodejci> sellers;
    private List<Auta> cars;
    private double money;

    /**
     * Constructor
     * 
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
        return this.money;
    }

    /**
     * Method for getting a name
     * 
     * @return String
     */
    public String getName() {
        return this.name;
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
     * @throws ExceptionInputOutput
     * @throws ExceptionFileNotFound
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
        return this.money = value + this.money;
    }

    /**
     * Method for getting a specific car
     * 
     * @param specificCar
     * @return Object Auta
     */
    public Auta getSpecificCar(int specificCar) {
        return getCarsSortedByBrand().get(specificCar - 1);
    }

    /**
     * Method for printing sellers
     * 
     * @return String s
     * @throws ExceptionInputOutput
     * @throws ExceptionFileNotFound
     */
    public String printSellers() throws ExceptionFileNotFound, ExceptionInputOutput {
        System.setProperty("file.encoding", "UTF-8");
        StringBuilder s = new StringBuilder();
        int count = 0;
        loadSellsers();
        ui.AutobazarApp.displaySellersHead();
        for (Prodejci seller : sellers) {
            if (count < 9) {
                s.append(count += 1).append(".  ").append(seller);
            } else {
                s.append(count += 1).append(". ").append(seller);
            }
        }

        return s.toString();
    }

    /**
     * Method for printing sorted sellers
     * 
     * @return String s
     */
    public String printSellersSorted() throws ExceptionFileNotFound, ExceptionInputOutput {
        StringBuilder s = new StringBuilder();
        int count = 0;
        sellersSortByExp();
        ui.AutobazarApp.displaySellersHead();
        for (Prodejci seller : getSellersSortedByExp()) {
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
    public void loadCars() throws ExceptionFileNotFound, ExceptionInputOutput {

        cars.clear();
        System.setProperty("file.encoding", "UTF-8");
        // čárku na tečku
        s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileCars))) {

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
     * Method for loading list of sellers and saving them into objects
     * 
     * @throws ExceptionFileNotFound
     * @throws ExceptionInputOutput
     */
    public void loadSellsers() throws ExceptionFileNotFound, ExceptionInputOutput {
        sellers.clear();
        System.setProperty("file.encoding", "UTF-8");
        // čárku na tečku
        s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSellers))) {

            String name, surname, age, exp;
            int iAge, iExp;

            while ((s = reader.readLine()) != null) {
                split = s.split(";");

                name = split[0];
                surname = split[1];
                age = split[2];
                iAge = Integer.parseInt(age);
                exp = split[3];
                iExp = Integer.parseInt(exp);

                sellers.add(new Prodejci(name,surname,iAge,iExp));
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
    public void saveToFile(File results) throws ExceptionInputOutput, IOException {

        // ukládat seznam prodejců s profitem, ukládat počet prodaných aut a jejich
        // celková cena, uložit profit bazaru, uložit čistý profit (všechno přes
        // StringBuiler)


        PdfWriter pdfWriter = new PdfWriter(results);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        
       /* FontProgram impact = FontProgramFactory.createFont(IMPACT_FONT);
        FontProgram liberation = FontProgramFactory.createFont(LIBERATIONSANS_REGULAR);

        PdfFont impactFont = PdfFontFactory.createFont(impact, PdfEncodings.UTF8);
        PdfFont liberationFont = PdfFontFactory.createFont(liberation, PdfEncodings.UTF8);
      */  
        String headder = "TÝDENNÍ VYÚČTOVÁNÍ";
        Paragraph para1 = new Paragraph(headder);

        Paragraph para2 = new Paragraph(printCarsSorted());

        document.add(para1);
        document.add(para2);

        document.close();
    }

    public void saveToBinary(File results) throws FileNotFoundException, IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(results, true))) {
            out.writeInt(cars.size());
            for (Auta car : cars) {
                out.writeUTF(car.getBrand());
                out.writeUTF(car.getModel());
                out.writeInt(car.getKilowatts());
            }
        }
    }

    public String readBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try (DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            int nBrand, nModel, nKilowatts = 0;
            int kilowatts = 1;
            String brand = "", model = "";
            while (!end) {
                try {
                    nBrand = in.readInt();
                    for (int i = 0; i < nBrand; i++) {
                        brand = in.readUTF();
                        nModel = in.readInt();
                        model = "";
                        for (int j = 0; j < nModel; j++) {
                            model += in.readUTF();
                            kilowatts = 0;
                            for (int k = 0; k < nKilowatts; k++) {
                                kilowatts += in.readInt();
                                sb.append(String.format("%10s%10s%5d", brand, model, kilowatts));
                            }
                        }
                    }
                    sb.append("\n");
                } catch (EOFException e) {
                    end = true;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Method for printing list of cars
     * 
     * @return String s
     * @throws ExceptionInputOutput
     * @throws ExceptionFileNotFound
     */
    public String printCars() throws ExceptionFileNotFound, ExceptionInputOutput {
        System.setProperty("file.encoding", "UTF-8");
        StringBuilder s = new StringBuilder();
        int count = 0;
        ui.AutobazarApp.displayCarsHead();
        loadCars();
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
     * Method for printing sorted cars
     * 
     * @return String s
     */
    public String printCarsSorted() throws ExceptionFileNotFound, ExceptionInputOutput {
        System.setProperty("file.encoding", "UTF-8");
        StringBuilder s = new StringBuilder();
        int count = 0;
        ui.AutobazarApp.displayCarsHead();
        carsSortByBrand();
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
        for(Prodejci seller: sellers) {
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
     * @throws ExceptionInputOutput
     * @throws ExceptionFileNotFound
     */
    public void sellTime(Prodejci seller, Auta car) throws ExceptionNoMoreSale, ExceptionFileNotFound, ExceptionInputOutput {
        sellersSortByExp();
        carsSortByBrand();
        double priceModif;
        double sellerCommision;

            if (seller.getExp() <= 10 && seller.getExp() >= 8) {
                // java.time, čas prodeje auta (random od 9:00 do 17:00), zapisovat do konzole
                priceModif = car.getPrice() * (pickRandomPercent(5, 0) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                seller.commission(sellerCommision);
                System.out.println(seller.getMoney());
                bankAccount(priceModif - sellerCommision);
                // cars.remove(car);
            } else if (seller.getExp() <= 7 && seller.getExp() >= 4) {
                priceModif = car.getPrice() * (pickRandomPercent(10, 6) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                seller.commission(sellerCommision);
                bankAccount(priceModif - sellerCommision);
                // cars.remove(car);
            } else {
                priceModif = car.getPrice() * (pickRandomPercent(15, 11) / 100);
                sellerCommision = seller.commission(priceModif * 0.1);
                seller.commission(sellerCommision);
                bankAccount(priceModif - sellerCommision);
                // cars.remove(car);
            }
        }
    


    // zprovoznit pdf ukládání
    // saveToBinary a readFromBinary do konzole, bude fungovat spolu s ukládáním do
    // .pdf, uloží se tam stejná data

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

    public static void main(String[] args) throws ExceptionInputOutput, ExceptionNoMoreSale, IOException {

        System.setProperty("file.encoding", "UTF-8");
        Autobazar abc = new Autobazar("ABC");
        abc.sellersSortByExp();
        System.out.println(abc.printSellers());
        abc.carsSortByBrand();
        System.out.println(abc.printCars());
        abc.sellTime(abc.getSpecificSeller(2), abc.getRandomCar());
        System.out.println(abc.getSpecificSeller(2).getMoney());
        System.out.println(abc.getMoney());
        //abc.saveToBinary(fileResults);
        //System.out.println(abc.readBinaryResults(fileResults));
        //System.out.println(abc.getSpecificCar(8));
        //abc.saveToFile(fileResultsPdf);
        System.out.println("Hi");
        System.out.println(abc);
    }
}
