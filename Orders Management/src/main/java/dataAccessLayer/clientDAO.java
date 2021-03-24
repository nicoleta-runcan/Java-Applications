package  dataAccessLayer;

import Model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Clasa in care se executa operatii asupra tabelului "Client": INSERT, DELETE, SELECT*/
public class clientDAO {
    protected static final Logger LOGGER = Logger.getLogger(clientDAO.class.getName());
    /** Variabila statica initializata cu un Query de insert*/
    private static final String insertStatementString = "INSERT INTO Client (idClient,clientName,clientAdress)"
            + " VALUES (?,?,?)";
    /**Variabila statica initializata cu un Query de delete in functie de numele Clientului*/
    private static final String deleteStatementString="DELETE FROM Client WHERE clientName=?";
    /**Variabila statica initializata cu un Query de Select *  din clasa Client*/
    private final static String findStatementString = "SELECT * FROM Client";

    /**
     * @param client
     * Metoda statica in care se lucreaza cu insertStatementString-ul de insert care insereaza un rand in tabelul
     * din baza de date care contine 3 valori:id, nume si adresa. Se seteaza aceste trei valori prin apelarea
     * metodelor mutatoare a parametrului de tip Client. In caz ca inserarea nu s-a putut efectua va aparea un mesaj care
     * va contine motivul pentru care nu s-a putut efectua operatia
     */
    public static int insert(Client client) {
            Connection dbConnection = ConnectionFactory.getConnection();

            PreparedStatement insertStatement = null;
            int insertedId = -1;
            try {
                insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setInt(1, client.getClientID());
                insertStatement.setString(2, client.getClientName());
                insertStatement.setString(3, client.getClientAdress());
                insertStatement.executeUpdate();

                ResultSet rs = insertStatement.getGeneratedKeys();
                if (rs.next()) {
                    insertedId = rs.getInt(1);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "InsertClient:insert " + e.getMessage());
            } finally {
                ConnectionFactory.close(insertStatement);
                ConnectionFactory.close(dbConnection);
            }
            return insertedId;
    }

    /**
     * @param client
     * Metoda in care se lucreaza cu deleteStatementString-ul de delete care contine un query
     * care efectueaza stergerea unui rand din tabelul Client in functie de numele Clientului.
     * Numele care trebuie sters este dat ca si paramtru al metodei.
     */
    public static int deleteClient(String client) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, client);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DeleteProduct:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * @return Se returneaza un ArrayList cu toti clienti existenti in tabelul Client.
     * In aceasta metoda se lucreaza cu findStatementString-ul initializat cu query-ul de "SELECT * FROM Client".
     * Obiectul rs de tip ResultSet va contine rezultatul query-ului, care se obtine prin apelul metodei executeQuery()
     * Datele sunt extrase apoi in 3 variabile, cu ajutorul carora un nou obiect de tipul Client este creat si adaugat in
     * ArrayList */
    public static ArrayList<Client>find() {
         ArrayList<Client> clienti=new ArrayList<Client>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement= null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while( rs.next()) {
                int id = rs.getInt("idClient");
                String clientName = rs.getString("clientName");
                String clientAdress = rs.getString("clientAdress");
                clienti.add(new Client(id, clientName, clientAdress));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
      return clienti;
    }

    /**
     * @return numele coloanelor tabelei Client
     * @throws SQLException
     */
    public static String[] nameOfColumns() throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement= null;
        ResultSet rs = null;
        findStatement = dbConnection.prepareStatement(findStatementString);
        rs = findStatement.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();

        Integer columncount = meta.getColumnCount();

        int count = 1 ; // start counting from 1 always

        String[] columnNames = new String[columncount];

        while(count<=columncount){

            columnNames [count-1] = meta.getColumnLabel(count);
            //System.out.println(columnNames[count-1]);
            count++;

        }
        return columnNames;
    }

    /**
     * @return numarul de coloane a tabelului Client
     * @throws SQLException
     */
    public static int numberOfColumn() throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement= null;
        ResultSet rs = null;
        findStatement = dbConnection.prepareStatement(findStatementString);
        rs = findStatement.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();

        Integer columncount = meta.getColumnCount();
        return columncount;
    }



}
