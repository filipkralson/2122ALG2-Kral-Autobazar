package com.filipkral.autobazar.app;

/**
 * Class of Prodejci object
 * 
 * @author filip.kral
 */
public class Prodejci {

    private String name, surname;
    private int age, exp;
    private double money;

    /**
     * Constructor
     * 
     * @param name
     * @param surname
     * @param age
     * @param exp
     */
    public Prodejci(String name, String surname, int age, int exp) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.exp = exp;
    }

    /**
     * Defensive constructor
     * 
     * @param seller
     */
    public Prodejci(Prodejci seller) {
        this.exp = seller.exp;
        this.name = seller.name;
        this.age = seller.age;
        this.surname = seller.surname;
    }

    /**
     * Method for getting name of a each seller
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method for getting surname of a each seller
     * 
     * @return String
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Method for getting age of a each seller
     * 
     * @return int
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Method for getting experience of a each seller
     * 
     * @return int
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * Method for getting money of a each seller
     * 
     * @return this.money
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Method for setting name for a each seller
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting surname for a each seller
     * 
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Method for setting age for a each seller
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method for setting experience for a each seller
     * 
     * @param exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * Method for adding money
     * 
     * @param moneyToAdd
     * @return this.money
     */
    public double commission(double moneyToAdd) {

        return this.money = moneyToAdd + this.money;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-10d %-15d %-10.2f\n", getName(), getSurname(), getAge(), getExp(),getMoney());
    }

    /*
     * public static void main(String[] args) {
     * Prodejci prodejce = new Prodejci("Josef", "Schulin", 25, 4);
     * System.out.println(prodejce);
     * 
     * }
     */
}
