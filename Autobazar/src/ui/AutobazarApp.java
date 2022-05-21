package ui;

import java.io.File;
import java.util.Scanner;

import com.itextpdf.io.exceptions.IOException;

import utils.AutobazarEnum;
import utils.AutobazarInterface;
import utils.ExceptionInputOutput;
import utils.ExceptionNoMoreSale;
import app.Auta;
import app.Autobazar;
import app.Prodejci;

public class AutobazarApp {

    public static Scanner sc = new Scanner(System.in);
    public static final File fileResultsPdf = new File("../Autobazar/src/data/results.pdf");
    public static final File fileResultsBinary = new File("../Autobazar/src/data/results.dat");

    public static void main(String[] args)
            throws ExceptionInputOutput, ExceptionNoMoreSale, InterruptedException, IOException, java.io.IOException {
        // trycatch bloky pro vstupy, vlastní vyjímky
        // objekt autobazarinterface s jenom potřebnými metodami pro práci tad v UI
        System.setProperty("file.encoding", "UTF-8");
        AutobazarInterface abc = new Autobazar("ABC");
        abc.loadCars();
        abc.loadSellsers();


        /*
         * System.out.println("AHOJ, vyber si jméno pro tvůj autobazar: ");
         * AutobazarInterface abc = new Autobazar(sc.nextLine());
         * abc.loadCars();
         * abc.loadSellers();
         * 
         * System.out.println();
         * System.out.println("Zápis proběhl úspěšně!");
         * 
         * System.out.println(abc);
         * System.out.println();
         */

        String choose = "";
        Boolean cont = true;



        while (cont) {
            System.setProperty("file.encoding", "UTF-8");
            System.out.println("Zadejte možnost:");
            System.out.format("1. Seznam aut\t\t[SA/sa]\n");
            System.out.format("2. Seznam prodejců\t[SP/sp]\n");
            System.out.format("3. Prodej\t\t[P/p]\n");
            System.out.format("0: Konec\t\t[K/k]\n\n");
            choose = sc.next();

            if (setChoose(choose).equalsIgnoreCase("SA")) {
                System.out.println("Chceš seřazený nebo originální seznam? [S/O]");
                choose = sc.next();
                if (setChoose(choose).equalsIgnoreCase("S")) {
                    System.setProperty("file.encoding", "UTF-8");
                    System.out.println();
                    System.out.println(abc.printCarsSorted());
                } else if (setChoose(choose).equalsIgnoreCase("O")) {
                    System.setProperty("file.encoding", "UTF-8");
                    System.out.println();
                    System.out.println(abc.printCars());
                } else {
                    throw new ExceptionInputOutput("Zadejte požadovaná data!");
                }
            } else if (setChoose(choose).equalsIgnoreCase("SP")) {
                System.setProperty("file.encoding", "UTF-8");
                System.out.println();
                System.out.println(abc.printSellersSorted());
            } else if (setChoose(choose).equalsIgnoreCase("P")) {
                Boolean contSale = true;
                if (abc.getCarsCount() == 0) {
                    throw new ExceptionNoMoreSale("Nemáš co prodávat! :(");
                } else {
                    while (contSale) {
                        System.out.println("Přeješ si pokračovat?");
                        choose = sc.next();
                        if (setChoose(choose).equalsIgnoreCase("A")) {
                            System.setProperty("file.encoding", "UTF-8");
                            Auta car = new Auta(abc.getRandomCar());

                            System.out.println(abc.printSellersSorted());
                            System.out.println("Vyber prodejce:");
                            Prodejci seller = new Prodejci(abc.getSpecificSeller(sc.nextInt()));    //try catch
                            
                            abc.sellTime(seller, car);
                            System.out.format("\nBylo prodáno auto: \n%s\ncelková provize autobazaru činní: %.2f.\n\n",
                                    car.toString(),
                                    abc.getMoney());
                                    //nepočítá přesně seller.getMoney();
                            System.out.println(seller.toString() + seller.getMoney());
                        } else {
                            contSale = false;
                        }
                    }
                }

            } else if (setChoose(choose).equalsIgnoreCase("K")) {
                System.out.println("Dobře, vytvářím .pdf a .dat dokumenty");
                // abc.saveToFile();
                //abc.saveToBinary(fileResultsBinary);
                //System.out.println(abc.readBinaryResults(fileResultsBinary));
                System.out.println("KONEC");
                cont = false;
            } else {
                throw new ExceptionInputOutput("Zadejte požadovaná data!");
            }
        }

    }

    /**
     * Method for comparing value in enum with input String
     * 
     * @param choose
     * @return String
     */
    public static String setChoose(String choose) {
        if (choose.equalsIgnoreCase("SA")) {
            return String.valueOf(AutobazarEnum.SA);
        } else if (choose.equalsIgnoreCase("SP")) {
            return String.valueOf(AutobazarEnum.SP);
        } else if (choose.equalsIgnoreCase("P")) {
            return String.valueOf(AutobazarEnum.P);
        } else if (choose.equalsIgnoreCase("K")) {
            return String.valueOf(AutobazarEnum.K);
        } else if (choose.equalsIgnoreCase("S")) {
            return String.valueOf(AutobazarEnum.S);
        } else if (choose.equalsIgnoreCase("O")) {
            return String.valueOf(AutobazarEnum.O);
        } else if (choose.equalsIgnoreCase("A")) {
            return String.valueOf(AutobazarEnum.A);
        } else {
            return String.valueOf(AutobazarEnum.N);
        }
    }

    /**
     * Method for printing sellers header
     */
    public static void displaySellersHead() {
        System.out.format("\n%-18s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Method for printing cars header
     */
    public static void displayCarsHead() {
        System.out.format("%-19s %-15s %-7s %-7s %-15s %-15s %-15s %-20s %-15s\n", "Značka", "Model", "Objem",
                "KW", "Kilometry", "Rok Výroby", "Palivo", "Barva", "Cena");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }

}
