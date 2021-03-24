package businessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Stock;
import dataAccessLayer.stockDAO;

public class BllStock {

    /**Aceasta clasa are un constructor gol si 6 metode in care se apeleaza metodele din clasa orderDAO prin care se executa
     * anumite operatii asupra tabelei "Stock"*/
    public BllStock()
    {

    }

    /**Apeleaza metoda statica de inserare in tabela Stock, din clasa stockDAO*/
    public int insertStock(Stock stock)
    {
        return stockDAO.insert(stock);
    }

    /**Apeleaza metoda statica de stergere in tabela Order, din clasa stockDAO*/
    public int deleteStock(String stock)
    {
        return stockDAO.deleteStock(stock);
    }

    /**Apeleaza metoda statica de update a cantitatii prodului in cazul unei comenzi, din clasa stockDAO*/
    public int updateStockOrder(String produs, int cantitate)
    {
        return stockDAO.updateStockOrder(produs, cantitate);
    }

    /**Apeleaza metoda statica de update a cantitatii prodului in cazul inserarii unui produs cu acelasi nume, din clasa stockDAO*/
    public int updateStockInsert(String produs, int cantitate)
    {
        return stockDAO.updateStockInsert(produs, cantitate);

    }

    /**Apeleaza metoda statica de selectare a tuturor datelor din tabela Stock, din clasa stockDAO*/
    public ArrayList<Stock> getStocks()
    {
        return stockDAO.find();
    }

    /**Apeleaza metoda statica de validare a operatiei de inserare in tabela Order, din clasa stockDAO(detaliata in aceasta clasa)*/
    public int isValid(String produs, int cantitate)
    {
        return stockDAO.isValid(produs, cantitate);
    }


}
