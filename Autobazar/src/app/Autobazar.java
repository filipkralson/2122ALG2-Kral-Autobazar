package app;

import java.util.ArrayList;

public class Autobazar {

    private String name;
    private int carsCount;
    static ArrayList<Prodejci> sellers;
    static ArrayList<Auta> cars;

    public Autobazar(String name, int carsCount) {
        this.name = name;
        this.carsCount = carsCount;
    }

    public int getCars() {
        return carsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addSeller(Prodejci seller) {    //exception, aby nebylo možné zadat stejné prodejce
        sellers.add(seller);
    }

    public int getSellersCount() {
        return sellers.size();
    }

    public double bankAccount() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Autobazar %s má %d aut a %d prodejců.\n",this.name,this.carsCount,getSellersCount());
    }

    public String printSellers() {
        StringBuilder s = new StringBuilder();

        for(Prodejci seller : sellers) {
            s.append(seller);
        }

        return s.toString();
    }

    public static void main(String[] args) {
        sellers = new ArrayList<>();
        Autobazar abc = new Autobazar("ABC", 15);
        abc.addSeller(new Prodejci("Josef", "Krátký", 30, 10));
        abc.addSeller(new Prodejci("Arnošta", "z Pardubic", 55, 8));

        System.out.println(abc);
        System.out.println(abc.printSellers());

    }
}
