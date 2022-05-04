package app;

public class Prodejci implements Comparable<Prodejci> {

    private String name, surname;
    private int age, exp;
    private int carSale;
    private double money;

    public Prodejci(String name, String surname, int age, int exp) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.exp = exp;
    }

    public Prodejci(Prodejci seller) {
        this.exp = seller.getExp();
        this.carSale = seller.getCarSale();
        this.money = seller.getMoney();
        this.name = seller.getName();
        this.age = seller.getAge();
        this.surname = seller.getSurname();
    }

    public int getCarSale() {
        return carSale;
    }

    public void setCarSale(int carSale) {
        this.carSale = carSale;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public int getExp() {
        return this.exp;
    }

    public double getMoney() {
        return this.money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-10d %-10d\n", getName(), getSurname(), getAge(), getExp());
    }


    public void commission(double moneyToAdd) {

        this.money += moneyToAdd;
    }

    public static void main(String[] args) {
        Prodejci prodejce = new Prodejci("Josef", "Schulin", 25, 4);
        System.out.println(prodejce);

    }

    @Override
    public int compareTo(Prodejci o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
