package com.filipkral.autobazar.utils;

import java.io.FileNotFoundException;

import com.itextpdf.io.exceptions.IOException;

import com.filipkral.autobazar.app.Auta;
import com.filipkral.autobazar.app.Prodejci;

/**
 * Interface for Autobazar
 * 
 * @author filip.kral
 */
public interface AutobazarInterface {

    public String printCars() throws ExceptionFileNotFound, ExceptionInputOutput;
    public String printCarsSorted() throws ExceptionFileNotFound, ExceptionInputOutput;
    public String printSellersSorted() throws ExceptionFileNotFound, ExceptionInputOutput ;
    public void sellTime(Prodejci seller, Auta car) throws ExceptionNoMoreSale, ExceptionFileNotFound, ExceptionInputOutput;
    public Prodejci getSpecificSeller(int specificSellerNumber);
    public String toString();
    public void loadCars() throws ExceptionFileNotFound, ExceptionInputOutput;
    public Auta getRandomCar();
    public double getMoney();
    public void saveToFile() throws ExceptionInputOutput, IOException, java.io.IOException;
    public void saveToBinary() throws FileNotFoundException, IOException, java.io.IOException;
    public String readBinaryResults() throws FileNotFoundException, IOException, java.io.IOException;
    public int getCarsCount();
    public void loadSellsers() throws ExceptionFileNotFound, ExceptionInputOutput;
    public String saleTime();

    
}
