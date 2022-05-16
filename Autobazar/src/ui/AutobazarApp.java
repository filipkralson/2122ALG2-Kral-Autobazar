package ui;

import java.util.Scanner;

import utils.AutobazarEnum;

public class AutobazarApp {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // trycatch bloky pro vstupy, vlastní vyjímky

        String choose = "";
        Boolean cont = true;

        while (cont) {
            System.out.println("Zadejte možnost:");
            System.out.format("1. Seznam aut\t\t[SA/sa]\n");
            System.out.format("2. Seznam prodejců\t[SP/sp]\n");
            System.out.format("3. Prodej\t\t[P/p]\n");
            System.out.format("0: Konec\t\t[K/k]\n");
            choose = sc.nextLine();
        
            if (choose.equalsIgnoreCase("SA")) {
                System.out.println("Seznam aut");
            } else if (choose.equalsIgnoreCase("SP")) {
                System.out.println("Seznam prodejců");
            } else if (choose.equalsIgnoreCase("P")){
                System.out.println("Prodej");
            } else {
                cont = false;
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
        } else if (choose.equalsIgnoreCase("P")){
            return String.valueOf(AutobazarEnum.P);
        } else {
            return String.valueOf(AutobazarEnum.K);
        }
    }

    /**
     * Method for printing sellers header
     */
    public static void displaySellersHead() {
        System.out.format("\n%-10s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti");
        System.out.println("------------------------------------------------");
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
