package dataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Product;
/**Clasa in care se executa operatii asupra tabelului "Product": INSERT, DELETE, SELECT*/
public class productDAO {

    protected static final Logger LOGGER = Logger.getLogger(productDAO.class.getName());
    /** Variabila statica initializata cu un Query de insert*/
    private static final String insertStatementString = "INSERT INTO Product (productName,productPrice)"
            + " VALUES (?,?)";
    /**Variabila statica initializata cu un Query de delete in functie de numele produsului*/
    private static final String deleteStatementString="DELETE FROM Product WHERE productName=?";
    /**Variabila statica initializata cu un Query de Select *  din clasa Product*/
    private final static String findStatementString = "SELECT * FROM Product";

    /**
     * @param product
     * Metoda statica in care se lucreaza cu insertStatementString-ul de insert care insereaza un rand in tabelul
     * din baza de date care contine 2 valori: numele produsului si pretul. Se seteaza aceste doua valori prin apelarea
     * metodelor mutatoare a parametrului de tip Client. In caz ca inserarea nu s-a putut efectua va aparea un mesaj care
     * va contine motivul pentru care nu s-a putut efectua operatia
     */
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName_product());
            insertStatement.setDouble(2, product.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "InsertProductt:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * @param product
     * Metoda in care se lucreaza cu deleteStatementString-ul de delete care contine un query
     * care efectueaza stergerea unui rand din tabelul Product in functie de numele produsului.
     * Numele care trebuie sters este dat ca si paramtru al metodei.
     */
    public static int deleteProduct(String product)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int insertedId = -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, product);
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
     * @return Se returneaza un ArrayList cu toate produsele existente in tabelul Product.
     * In aceasta metoda se lucreaza cu findStatementString-ul initializat cu query-ul de "SELECT * FROM Product".
     * Obiectul rs de tip ResultSet va contine rezultatul query-ului, care se obtine prin apelul metodei executeQuery()
     * Datele sunt extrase apoi in 2 variabile, cu ajutorul carora un nou obiect de tipul Product este creat si adaugat in
     * ArrayList */
    public static ArrayList<Product> find() {
        ArrayList<Product> produse=new ArrayList<Product>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while( rs.next()) {
                String productName= rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                produse.add(new Product(productName, productPrice));

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return produse;
    }

    /**
     * @return numele coloanelor tabelei Product
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

        String[] columnNames = new String[columncount+1];

        while(count<=columncount){

            columnNames [count-1] = meta.getColumnLabel(count);
            count++;

        }
        return columnNames;
    }

    /**
     * @return numarul de coloane a tabelului Product
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
