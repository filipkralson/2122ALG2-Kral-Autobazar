package utils;

import java.io.File;
import java.io.FileNotFoundException;

import com.itextpdf.io.exceptions.IOException;

import app.Auta;
import app.Prodejci;

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
    public void saveToFile(File results) throws ExceptionInputOutput, IOException, java.io.IOException;
    public void saveToBinary(File results) throws FileNotFoundException, IOException, java.io.IOException;
    public String readBinaryResults(File results) throws FileNotFoundException, IOException, java.io.IOException;
    public int getCarsCount();
    public void loadSellsers() throws ExceptionFileNotFound, ExceptionInputOutput;
    public String saleTime();
    public void setEncoding();

    
}
