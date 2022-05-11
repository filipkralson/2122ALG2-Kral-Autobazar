package ui;

public class AutobazarApp{
    public static void main(String[] args) {
        //trycatch bloky pro vstupy, vlastní vyjímky


    }

    public static void displaySellersHead() {
        System.out.format("\n%-10s %-15s %-10s %-10s\n", "Jméno", "Příjmení", "Věk", "Zkušenosti");
        System.out.println("------------------------------------------------");
    }

    public static void displayCarsHead() {
        System.out.format("%-19s %-15s %-7s %-7s %-15s %-15s %-15s %-20s %-15s\n", "Značka", "Model", "Objem",
                "KW", "Kilometry", "Rok Výroby", "Palivo", "Barva", "Cena");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

    }
    
}
