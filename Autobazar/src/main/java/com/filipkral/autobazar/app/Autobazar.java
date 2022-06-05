package com.filipkral.autobazar.app;

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
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.filipkral.autobazar.utils.AutobazarInterface;
import com.filipkral.autobazar.utils.ExceptionFileNotFound;
import com.filipkral.autobazar.utils.ExceptionInputOutput;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

/**
 * Class with common Autobazar methods
 * 
 * @author filip.kral
 * 
 */
public class Autobazar implements AutobazarInterface {

    public static final Collator col = Collator.getInstance(new Locale("cs", "CZ"));
    public static final String IMPACT_FONT = "./Autobazar/src/main/java/com/filipkral/autobazar/fonts/impact.ttf";
    public static final String LIBERATIONSANS_REGULAR = "./Autobazar/src/main/java/com/filipkral/autobazar/fonts/LiberationSans-Regular.ttf";
    public static final String LIBERATIONSANS_BOLD = "./Autobazar/src/main/java/com/filipkral/autobazar/fonts/LiberationSans-Bold.ttf";
    public static final Comparator<Auta> COMP_BY_BRAND = (Auta c1, Auta c2) -> {
        int value = col.compare(c1.getBrand(), c2.getBrand());
        if (value == 0) {
            value = col.compare(c1.getModel(), c2.getModel());
        }
        return value;
    };
    public static final Comparator<Prodejci> COMP_BY_EXP = (Prodejci p1, Prodejci p2) -> {
        if ((p2.getExp() - p1.getExp()) == 0) {
            return p2.getAge() - p1.getAge();
        }
        return p2.getExp() - p1.getExp();
    };

    private static final File fileSellers = new File(
            "./Autobazar/src/main/java/com/filipkral/autobazar/sellers.csv");
    private static final File fileCarsUSA = new File("./Autobazar/src/main/java/com/filipkral/autobazar/carsUSA.csv");
    private static final File fileResultsBinary = new File(
            "./Autobazar/src/main/java/com/filipkral/autobazar/data/results.dat");
    private static final Locale loc = new Locale("CS", "cz");
    private static File fileToPdf;

    private Random random;
    private String name, s;
    private String[] split;
    private List<Prodejci> sellers;
    private List<Auta> cars;
    private double money;
    private int soldCars;

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
     * Method for getting sold cars
     * 
     * @return int
     */
    public int getSoldCars() {
        return soldCars;
    }

    /**
     * Method for setting sold cars
     * 
     */
    public void setSoldCars() {
        this.soldCars = 1 + this.soldCars;
    }

    /**
     * Method for getting money
     * 
     * @return double
     */
    @Override
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
    public void addSeller(Prodejci seller) {
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
    @Override
    public int getSellersCount() {
        return sellers.size();
    }

    /**
     * Method for getting count of cars
     * 
     * @return int
     * @throws ExceptionInputOutput
     */
    @Override
    public int getCarsCount() {
        return cars.size();
    }

    /**
     * Method for saving money
     * 
     * @param value
     * @return double
     */
    public double bankAccount(double moneyToAdd) {
        try {
            money += moneyToAdd;
            return money;
        } catch (ExceptionInputOutput e) {
            return 0;
        }

    }

    /**
     * Method for getting a specific car
     * 
     * @param specificCar
     * @return Object Auta
     */
    public Auta getSpecificCar(int specificCar) {
        try {
            return getCarsSortedByBrand().get(specificCar - 1);
        } catch (ExceptionInputOutput e) {
            return null;
        }

    }

    /**
     * Method for printing sellers
     * 
     * @return String s
     * @throws ExceptionInputOutput
     * @throws ExceptionFileNotFound
     */
    public String printSellers() throws ExceptionFileNotFound, ExceptionInputOutput {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        com.filipkral.autobazar.ui.AutobazarApp.displaySellersHead();
        for (Prodejci seller : sellers) {
            if (count < 9) {
                sb.append(count += 1).append(".  ").append(seller);
            } else {
                sb.append(count += 1).append(". ").append(seller);
            }
        }

        return sb.toString();
    }

    /**
     * Method for printing sorted sellers
     * 
     * @return String s
     * @throws com.filipkral.autobazar.utils.ExceptionFileNotFound
     */
    @Override
    public String printSellersSorted() throws ExceptionFileNotFound, ExceptionInputOutput {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append(com.filipkral.autobazar.ui.AutobazarApp.displaySellersHeadForExport());
        for (Prodejci seller : getSellersSortedByExp()) {
            if (count < 9) {
                sb.append(count += 1).append(".  ").append(seller);
            } else {
                sb.append(count += 1).append(". ").append(seller);
            }
        }

        return sb.toString();
    }

    /**
     * Method for loading cars from US dataset
     * 
     * @throws com.filipkral.autobazar.utils.ExceptionFileNotFound
     */
    @Override
    public void loadCars() throws ExceptionFileNotFound, ExceptionInputOutput {
        s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileCarsUSA, StandardCharsets.UTF_8))) {
            String brand, model, year, milage, color, price, vin, state, country;
            int intYear, intPrice;
            double dblMilage;

            while ((s = reader.readLine()) != null) {
                split = s.split(",");

                price = split[1];
                intPrice = Integer.parseInt(price);
                brand = split[2];
                model = split[3];
                year = split[4];
                intYear = Integer.parseInt(year);
                milage = split[6];
                dblMilage = Double.parseDouble(milage);
                color = split[7];
                vin = split[8];
                state = split[10];
                country = split[11];

                cars.add(new Auta(brand, model, vin, dblMilage, intYear, color, intPrice, state, country));
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
    @Override
    public void loadSellsers() throws ExceptionFileNotFound, ExceptionInputOutput {
        s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileSellers, StandardCharsets.UTF_8))) {

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

                sellers.add(new Prodejci(name, surname, iAge, iExp));
            }
            reader.close();
        } catch (ExceptionFileNotFound e) {
            throw new ExceptionFileNotFound("Zadaný soubor nebyl nalezen!");
        } catch (IOException e) {
            throw new ExceptionInputOutput("Chyba při vstupu!");
        }

    }

    /**
     * Method for showing JFileChooser dialog for saving a .pdf file
     */
    public static void saveToPdfWindow() {

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setDialogTitle("Uložit jako");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToPdf = fileChooser.getSelectedFile();
        }
    }

    /**
     * Method for saving week results into .pdf, use of itextpdf library
     * 
     * @throws ExceptionInputOutput
     * @throws IOException
     */
    @Override
    public void saveToFile() throws ExceptionInputOutput, IOException {

        saveToPdfWindow();

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(fileToPdf));
        Document document = new Document(pdfDocument);

        FontProgram impact = FontProgramFactory.createFont(IMPACT_FONT);
        FontProgram liberation = FontProgramFactory.createFont(LIBERATIONSANS_REGULAR);

        PdfFont impactFont = PdfFontFactory.createFont(impact, PdfEncodings.IDENTITY_H);
        PdfFont liberationFont = PdfFontFactory.createFont(liberation,
                PdfEncodings.IDENTITY_H);

        Text title = new Text("TÝDENNÍ VYÚČTOVÁNÍ AUTOBAZARU\t\t").setFont(impactFont);
        Text name = new Text(getName()).setFont(impactFont);
        Paragraph titleText = new Paragraph(title).add(name);

        String carsCount = String.valueOf(getSoldCars());
        String dblMoney = String.valueOf(String.format(loc, "%.2f", getMoney()));
        String dblTotal = String.valueOf(String.format(loc, "%.2f", getMoney() - sellersMoney()));

        Text sellers = new Text(printSellersSorted() + "\n").setFont(liberationFont);
        Text ks = new Text("Bylo prodáno: " + carsCount + " ks\n").setFont(liberationFont);
        Text ksMoney = new Text(String.format(loc, "za: %s Kč,-\n", dblMoney)).setFont(liberationFont);
        Text totalMoney = new Text(String.format(loc, "Celkový výnos je: %s Kč,-\n", dblTotal)).setFont(liberationFont)
                .setBold();

        Paragraph in = new Paragraph().add(sellers).add(ks).add(ksMoney).add(totalMoney);

        document.add(titleText);
        document.add(in);
        document.close();
    }

    /**
     * Method for saving week results into .bin file
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    @Override
    public void saveToBinary() throws FileNotFoundException, IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileResultsBinary))) {
            out.writeUTF(printSellersSorted());
            out.writeUTF("Bylo prodáno: ");
            out.writeInt(getSoldCars());
            out.writeUTF(" ks");
            out.writeUTF("za: ");
            out.writeDouble(getMoney());
            out.writeUTF("Celkový výnos je: ");
            out.writeDouble(getMoney() - sellersMoney());
        } catch (EOFException e) {
            throw new EOFException("Chyba při zápisu!");
        }
    }

    /**
     * Method for reading binary file
     * 
     * @return String sb
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    @Override
    public String readBinaryResults() throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileResultsBinary))) {
            sb.append("\n");
            sb.append(in.readUTF());
            sb.append("\n");

            sb.append(in.readUTF()).append(in.readInt()).append(in.readUTF()).append("\n").append(in.readUTF())
                    .append(String.format(loc, "%.2f", in.readDouble())).append("\n")
                    .append(in.readUTF()).append(String.format(loc, "%.2f", in.readDouble())).append("\n");
        } catch (EOFException e) {
            throw new EOFException("Chyba při čtení!");
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
        StringBuilder sb = new StringBuilder();
        int count = 0;
        com.filipkral.autobazar.ui.AutobazarApp.displayCarsHead();
        for (Auta car : cars) {
            if (count < 9) {
                sb.append(count += 1).append(".  ").append(car);
            } else {
                sb.append(count += 1).append(". ").append(car);
            }

        }

        return sb.toString();
    }

    /**
     * Method for printing sorted cars
     * 
     * @return String s
     * @throws com.filipkral.autobazar.utils.ExceptionFileNotFound
     */
    @Override
    public String printCarsSorted() throws ExceptionFileNotFound, ExceptionInputOutput {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        com.filipkral.autobazar.ui.AutobazarApp.displayCarsHead();
        for (Auta car : getCarsSortedByBrand()) {
            if (count < 9) {
                sb.append(count += 1).append(".  ").append(String.format(loc, "%s", car));
            } else {
                sb.append(count += 1).append(". ").append(String.format(loc, "%s", car));
            }
            
        }

        return sb.toString();
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
     * Method for copy of cars list
     * 
     * @return ArrayList copy
     */
    public ArrayList<Auta> getCars() {
        ArrayList<Auta> copy = new ArrayList<>();
        for (Auta car : cars) {
            copy.add(car);
        }
        return copy;
    }

    /**
     * Method for copy of sellers list
     * 
     * @return ArrayList copy
     */
    public ArrayList<Prodejci> getSellers() {
        ArrayList<Prodejci> copy = new ArrayList<>();
        for (Prodejci seller : sellers) {
            copy.add(seller);
        }
        return copy;

    }

    /**
     * Method for getting money from all sellers
     * 
     * @return money
     */
    public double sellersMoney() {
        double money = 0;
        for (Prodejci seller : getSellersSortedByExp()) {
            money += seller.getMoney();
        }
        return money;
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
        return new ArrayList<Auta>(cars);
    }

    /**
     * Method for getting a random car
     * 
     * @return Object Auta
     */
    @Override
    public Auta getRandomCar() {
        carsSortByBrand();
        return cars.get(new Random().nextInt(cars.size()));
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
    @Override
    public Prodejci getSpecificSeller(int specificSellerNumber) {
        try {
            return getSellersSortedByExp().get(specificSellerNumber - 1);
        } catch (ExceptionInputOutput e) {
            return null;
        }

    }

    @Override
    /**
     * Method for printing time of sale
     * 
     * @return String timeFormated
     * 
     */
    public String saleTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timeFormated = time.format(formatTime);
        return timeFormated;
    }

    /**
     * Method for converting price of cars form USD do CZK
     * 
     * @param money
     * @return money
     */
    public double usdToCzk(double money) {
        return money * 23;
    }

    public double commission(Prodejci seller, double money) {
        return seller.commission(money);
    }

    /**
     * Method for making a sale
     * 
     * @param seller
     * @param car
     * @throws ExceptionInputOutput
     */
    @Override
    public void sellTime(Prodejci seller, Auta car) {
        double priceModif;
        double sellerCommission;

        if (seller.getExp() <= 10 && seller.getExp() >= 8) {
            priceModif = usdToCzk(car.getPrice()) * (pickRandomPercent(5, 0) / 100);
            sellerCommission = seller.commission(priceModif * 0.01);
            seller.commission(sellerCommission);
            bankAccount((priceModif - sellerCommission));
            setSoldCars();
            cars.remove(car);
        } else if (seller.getExp() <= 7 && seller.getExp() >= 4) {
            priceModif = usdToCzk(car.getPrice()) * (pickRandomPercent(10, 6) / 100);
            sellerCommission = seller.commission(priceModif * 0.01);
            seller.commission(sellerCommission);
            bankAccount((priceModif - sellerCommission));
            setSoldCars();
            cars.remove(car);
        } else {
            priceModif = usdToCzk(car.getPrice()) * (pickRandomPercent(15, 11) / 100);
            sellerCommission = seller.commission(priceModif * 0.01);
            seller.commission(sellerCommission);
            bankAccount((priceModif - sellerCommission));
            setSoldCars();
            cars.remove(car);
        }
    }

    @Override
    /**
     * Method toString
     * 
     * @return String
     */
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n", getName(), getCarsCount(), getSellersCount());
    }

   /* 
   public static void main(String[] args) throws ExceptionInputOutput,
            IOException {
        Autobazar abc = new Autobazar("ABC");
        abc.loadCars();
        abc.loadSellsers();
        System.out.println(abc.printSellersSorted());
        System.out.println(abc.printCarsSorted());
        abc.sellTime(abc.getSpecificSeller(1), abc.getRandomCar());
        System.out.println(abc.getSpecificSeller(1).getMoney());
        abc.saveToBinary();
        System.out.println(abc.readBinaryResults());
        System.out.println("Hi");
        System.out.println(abc);
    }
    */ 
}