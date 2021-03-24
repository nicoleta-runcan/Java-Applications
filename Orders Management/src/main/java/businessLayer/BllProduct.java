package businessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Product;
import dataAccessLayer.productDAO;

/**Aceasta clasa are un constructor gol si 5 metode in care se apeleaza metodele din clasa orderDAO prin care se executa
 * anumite operatii asupra tabelei "Product"*/
public class BllProduct {

    public BllProduct() {
    }

    /**Apeleaza metoda statica de inserare in tabela Product, din clasa productDAO*/
    public int insertProduct(Product product)
    {
        return productDAO.insert(product);
    }

    /**Apeleaza metoda statica de stergere in tabela Order in functie de numele produsului, din clasa productDAO*/
    public int deleteProduct(String product)
    {
        return productDAO.deleteProduct(product);
    }

    /**Apeleaza metoda statica de selectare a tuturor datelor din tabela Product, din clasa productDAO*/
    public ArrayList<Product> getProducts()
    {
        return productDAO.find();
    }

    /**Apeleaza metoda care returneaza numarul de coloane a tabelei Product, din clasa productDAO*/
    public int getNumberOfColumns() throws SQLException {
        return productDAO.numberOfColumn();
    }

    /**Apeleaza medota care returneaza numele coloanelor tebalei Product, din clasa productDAO*/
    public String[] getColumnsNames() throws SQLException {
        return productDAO.nameOfColumns();
    }
}
