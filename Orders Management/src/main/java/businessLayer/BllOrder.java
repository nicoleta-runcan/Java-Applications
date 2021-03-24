package businessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Order;
import dataAccessLayer.orderDAO;

public class BllOrder {

    /**Aceasta clasa are un constructor gol si 6 metode in care se apeleaza metodele din clasa orderDAO prin care se executa
 * anumite operatii asupra tabelei "Order"*/
    public BllOrder()
    {

    }

    /**Apeleaza metoda statica de inserare in tabela Order, din clasa orderDAO*/
    public int insertOrder(Order order)
    {
        return orderDAO.insert(order);
    }

    /**Apeleaza metoda statica de stergere in tabela Order in fucntie de numele clientului, din clasa orderDAO*/
    public int deleteOrderClient(String client)
    {
        return orderDAO.deleteOrderClient(client);
    }

    /**Apeleaza metoda statica de stergere in tabela Order, din clasa orderDAO*/
    public int deleteOrderProduct(String product)
    {
        return orderDAO.deleteOrderProduct(product);
    }

    /**Apeleaza metoda statica de selectare a tuturor datelor din tabela Order, din clasa orderDAO*/
    public ArrayList<Order> getOrders()
    {
        return orderDAO.find();
    }

    /**Apeleaza metoda care returneaza numarul de coloane a tabelei Order, din clasa orderDAO*/
    public int getNumberOfColumns() throws SQLException {
        return orderDAO.numberOfColumn();
    }

    /**Apeleaza medota care returneaza numele coloanelor tebalei Order, din clasa orderDAO*/
    public String[] getColumnsNames() throws SQLException {
        return orderDAO.nameOfColumns();
    }

}
