package ui;

import java.util.Locale;
import java.util.Scanner;

import com.itextpdf.io.exceptions.IOException;

import utils.AutobazarEnum;
import utils.AutobazarInterface;
import utils.ExceptionInputMissmatch;
import utils.ExceptionInputOutput;
import utils.ExceptionNoMoreSale;
import app.Auta;
import app.Autobazar;
import app.Prodejci;

/**
 * UI class
 * 
 * @author filip.kral
 * 
 */
public class AutobazarApp {

    public static Scanner sc = new Scanner(System.in);
    public static final Locale loc = new Locale("CS", "cz");

    public static void main(String[] args)
            throws ExceptionInputOutput, ExceptionNoMoreSale, InterruptedException, IOException, java.io.IOException {
        
        AutobazarInterface abc = new Autobazar("ABC");
        abc.loadCars();
        abc.loadSellsers();
        
        String choose = "";
        Boolean cont = true;
    
        while (cont) {
            try {
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
                        System.out.println();
                        System.out.format(loc, abc.printCarsSorted());
                    } else if (setChoose(choose).equalsIgnoreCase("O")) {
                        System.out.println();
                        System.out.println(abc.printCars());
                    } else {
                        throw new ExceptionInputOutput("Zadejte požadovaná data!");
                    }
                } else if (setChoose(choose).equalsIgnoreCase("SP")) {
                    System.out.println();
                    System.out.println(abc.printSellersSorted());
                } else if (setChoose(choose).equalsIgnoreCase("P")) {
                    Boolean contSale = true;
                    if (abc.getCarsCount() == 0) {
                        throw new ExceptionNoMoreSale("Nemáš co prodávat! :(");
                    } else {
                        while (contSale) {
                            System.out.println("Přeješ si pokračovat? [A,a]");
                            choose = sc.next();
                            if (setChoose(choose).equalsIgnoreCase("A")) {
                                Auta car = new Auta(abc.getRandomCar());
                                System.out.println(abc.printSellersSorted());
                                System.out.println("Vyber prodejce:");
                                try {
                                    int choiceSeller = sc.nextInt();
                                    Prodejci seller = new Prodejci(abc.getSpecificSeller(choiceSeller));

                                    abc.sellTime(seller, car);
                                    System.out.format(
                                            "\nBylo prodáno auto: \n%s\ncelková provize autobazaru činní: %.2f.\n\n",
                                            car.toString(),
                                            abc.getMoney());
                                    System.out.format("%s\n",abc.saleTime());
                                } catch (ExceptionInputMissmatch e) {
                                    throw new ExceptionInputMissmatch("Zajdete správného prodejce!");
                                }
                            } else {
                                contSale = false;
                            }
                        }
                    }
                } else if (setChoose(choose).equalsIgnoreCase("K")) {
                    System.out.println("Dobře, vytvářím .pdf a .dat dokumenty");
                    //abc.saveToFile();
                    abc.saveToBinary();
                    System.out.println(abc.readBinaryResults());
                    System.out.println("KONEC");
                    cont = false;
                } else {
                    throw new ExceptionInputOutput("Zadejte požadovaná data!");
                }
            } catch (ExceptionInputOutput e) {
                throw new ExceptionInputOutput("Zadejte správný výběr!");
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
