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
            choose = sc.nextLine();

            if (choose.equalsIgnoreCase("SA")) {
                System.out.println("Seznam aut");
            } else if (choose.equalsIgnoreCase("SP")) {
                System.out.println("Seznam prodejců");
            } else {
                System.out.println("Prodej");
            }
            System.out.println("Přejete si ukončit výběr? a/n");

            if (sc.nextLine().equalsIgnoreCase("a")) {
                cont = false;
            } else {
                cont = true;
            }
        }

    }

    public static String setChoose(String choose) {
        if (choose.equalsIgnoreCase("SA")) {
            return String.valueOf(AutobazarEnum.SA);
        } else if (choose.equalsIgnoreCase("SP")) {
            return String.valueOf(AutobazarEnum.SP);
        } else {
            return String.valueOf(AutobazarEnum.P);
        }
    }

    public static void displaySellersHead() {
        System.out.format("\n%-10s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti");
        System.out.println("------------------------------------------------");
    }

    public static void displayCarsHead() {
        System.out.format("%-19s %-15s %-7s %-7s %-15s %-15s %-15s %-20s %-15s\n", "Značka", "Model", "Objem",
                "KW", "Kilometry", "Rok Výroby", "Palivo", "Barva", "Cena");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");

    }

}
