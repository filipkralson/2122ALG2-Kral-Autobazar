package app;

public class Prodejci implements Comparable<Prodejci>{

    private String name, surname;
    private int age, exp;
    private int carSale;
    private double money;

    public Prodejci(String name, String surname, int age, int exp) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.exp = exp;
        this.setCarSale(3);
    }

    public int getCarSale() {
        return carSale;
    }

    public void setCarSale(int carSale){    //exception
        this.carSale = 3;
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
        return String.format("Prodejce: %s %10s \t věk: %2d \t úroveň zkušeností: %2d\n",this.name,this.surname,this.age,this.exp);
    }

    public double commission(Prodejci prodejce /*výsledek z prodeje*/) {
        //použití setMoney
        return 0;
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
