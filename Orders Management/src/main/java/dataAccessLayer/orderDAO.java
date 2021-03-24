package dataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Order;

/**Clasa in care se executa operatii asupra tabelului "Client": INSERT, DELETE, SELECT*/
public class orderDAO {
    protected static final Logger LOGGER = Logger.getLogger(orderDAO.class.getName());
    /** Variabila statica initializata cu un Query de insert*/
    private static final String insertStatementString = "INSERT INTO OrderT(idOrder,clientName,productName, orderQuantity)"
            + " VALUES (?,?,?,?)";
    /**Variabila statica initializata cu un Query de delete in functie de numele Clientului*/
    private static final String firstDeleteStatementString="DELETE FROM OrderT WHERE clientName=?";
    /**Variabila statica initializata cu un Query de delete in functie de numele produsului comandat*/
    private static final String secondDeleteStatementString="DELETE FROM OrderT WHERE productName=?";
    /**Variabila statica initializata cu un Query de Select *  din clasa Client*/
    private final static String findStatementString = "SELECT * FROM OrderT";

    /**
     * @param order
     * Metoda statica in care se lucreaza cu insertStatementString-ul de insert care insereaza un rand in tabelul
     * din baza de date care contine 4 valori:id, numele cleintului, numele produsului si cantitatea produsului comandat.
     * Se seteaza aceste patru valori prin apelarea metodelor mutatoare a parametrului de tip Order.
     * In caz ca inserarea nu s-a putut efectua va aparea un mesaj care va contine motivul pentru care nu s-a putut efectua operatia.
     */
    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();
        //ArrayList<Product> produse=productDAO.find();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
                    insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
                    insertStatement.setInt(1, order.getIdOrder());
                    insertStatement.setString(2, order.getClientName());
                    insertStatement.setString(3, order.getProductName());
                    insertStatement.setInt(4, order.getOrderQuantity());
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
     * @param order
     * Metoda in care se lucreaza cu deleteStatementString-ul de delete in functie de numele clientului care contine un query
     * care efectueaza stergerea unui rand din tabelul Order in functie de numele Clientului.
     * Numele care trebuie sters este dat ca si paramtru al metodei.
     */
    public static int deleteOrderClient(String order)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(firstDeleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, order);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DeleteOrder:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;

    }

    /**
     * @param order
     * Metoda in care se lucreaza cu deleteStatementString-ul de delete care contine un query
     * care efectueaza stergerea unui rand din tabelul Client in functie de numele produsului comandat.
     * Numele care trebuie sters este dat ca si paramtru al metodei.
     */
    public static int deleteOrderProduct(String order)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(secondDeleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, order);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DeleteOrder:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;

    }

    /**
     * @return Se returneaza un ArrayList cu toate comenzile existente in tabelul Order.
     * In aceasta metoda se lucreaza cu findStatementString-ul initializat cu query-ul de "SELECT * FROM Order".
     * Obiectul rs de tip ResultSet va contine rezultatul query-ului, care se obtine prin apelul metodei executeQuery()
     * Datele sunt extrase apoi in 4 variabile, cu ajutorul carora un nou obiect de tipul Order este creat si adaugat in
     * ArrayList */
    public static ArrayList<Order> find() {
        ArrayList<Order> comenzi=new ArrayList<Order>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while( rs.next()) {
                int idOrder=rs.getInt("idOrder");
                String clientName = rs.getString("clientName");
                String productName = rs.getString("productName");
                int orderQuantity=rs.getInt("orderQuantity");
                comenzi.add(new Order(idOrder, clientName, productName, orderQuantity));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return comenzi;
    }

    /**
     * @return numele coloanelor tabelei Order
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

    //public static int delete();

}
