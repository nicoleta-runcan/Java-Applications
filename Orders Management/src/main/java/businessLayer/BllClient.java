package businessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Client;
import dataAccessLayer.clientDAO;

/**Aceasta clasa are un constructor gol si 5 metode in care se apeleaza metodele din clasa clientDAO prin care se executa
 * anumite operatii asupra tabelei "Client"*/
public class BllClient {

    public BllClient()
    {

    }

    /**Apeleaza metoda statica de inserare in tabela Client, din clasa clientDAO*/
    public int addClient(Client client)
    {
        return clientDAO.insert(client);
    }

    /**Apeleaza metoda statica de stergere in tabela Client, din clasa clientDAO*/
    public int deleteClient(String client)
    {
       return clientDAO.deleteClient(client);
    }

    /**Apeleaza metoda statica de selectare a tuturor datelor din tabela Client, din clasa clientDAO*/
    public ArrayList<Client> getClients()
    {
        return clientDAO.find();
    }

    /**Apeleaza metoda care returneaza numarul de coloane a tabelei Client, din clasa clientDAO*/
    public int getNumberOfColumns() throws SQLException {
        return clientDAO.numberOfColumn();
    }

    /**Apeleaza medota care returneaza numele coloanelor tebalei Client, din clasa clientDAO*/
    public String[] getColumnsNames() throws SQLException {
        return clientDAO.nameOfColumns();
    }
}
