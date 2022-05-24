package com.filipkral.autobazar.ui;

import java.util.Locale;
import java.util.Scanner;

import com.itextpdf.io.exceptions.IOException;

import com.filipkral.autobazar.utils.AutobazarEnum;
import com.filipkral.autobazar.utils.AutobazarInterface;
import com.filipkral.autobazar.utils.ExceptionInputMissmatch;
import com.filipkral.autobazar.utils.ExceptionInputOutput;
import com.filipkral.autobazar.utils.ExceptionNoMoreSale;
import com.filipkral.autobazar.app.Auta;
import com.filipkral.autobazar.app.Autobazar;
import com.filipkral.autobazar.app.Prodejci;

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
                    System.out.println("Chceš seřazený nebo originální seznam? [S,s/O,o]");
                    choose = sc.next();
                    if (setChoose(choose).equalsIgnoreCase("S")) {
                        System.out.println();
                        System.out.println(abc.printCarsSorted());
                    } else if (setChoose(choose).equalsIgnoreCase("O")) {
                        System.out.println();
                        System.out.println(abc.printCars());
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
                            try {
                                System.out.println("Přeješ si pokračovat? [A,a]");
                                choose = sc.next();
                                if (setChoose(choose).equalsIgnoreCase("A")) {
                                    Auta car = new Auta(abc.getRandomCar());
                                    System.out.println(abc.printSellersSorted());
                                    System.out.println("Vyber prodejce:");

                                    int choiceSeller = sc.nextInt();
                                    Prodejci seller = new Prodejci(abc.getSpecificSeller(choiceSeller));

                                    abc.sellTime(seller, car);
                                    System.out.format(
                                            "\nBylo prodáno auto: \n%s\ncelková provize autobazaru činní: %.2f.\n\n",
                                            car.toString(),
                                            abc.getMoney());
                                } else {
                                    contSale = false;
                                }
                            } catch (ExceptionInputMissmatch e) {
                                throw new ExceptionInputMissmatch("Zajdete správného prodejce!");
                            }

                        }
                    }
                } else if (setChoose(choose).equalsIgnoreCase("K")) {
                    System.out.println("Dobře, vytvářím .pdf a .dat dokumenty");
                    // abc.saveToFile();
                    abc.saveToBinary();
                    System.out.println(abc.readBinaryResults());
                    System.out.println("KONEC");
                    cont = false;
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
        String chosen;
        if (choose.equalsIgnoreCase("SA")) {
            chosen = String.valueOf(AutobazarEnum.SA);
        } else if (choose.equalsIgnoreCase("SP")) {
            chosen = String.valueOf(AutobazarEnum.SP);
        } else if (choose.equalsIgnoreCase("P")) {
            chosen = String.valueOf(AutobazarEnum.P);
        } else if (choose.equalsIgnoreCase("K")) {
            chosen = String.valueOf(AutobazarEnum.K);
        } else if (choose.equalsIgnoreCase("S")) {
            chosen = String.valueOf(AutobazarEnum.S);
        } else if (choose.equalsIgnoreCase("O")) {
            chosen = String.valueOf(AutobazarEnum.O);
        } else if (choose.equalsIgnoreCase("A")) {
            chosen = String.valueOf(AutobazarEnum.A);
        } else {
            chosen = String.valueOf(AutobazarEnum.N);
        }
        return chosen;
    }

    /**
     * Method for printing sellers header
     */
    public static void displaySellersHead() {
        System.out.format("\n%-18s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti");
        System.out.println("--------------------------------------------------------");
    }

    public static String displaySellersHeadForExport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n%-15s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti"));
        sb.append("-----------------------------------------------------\n");
        return sb.toString();
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
