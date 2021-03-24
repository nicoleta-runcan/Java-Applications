package dataAccessLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Stock;

/**Clasa in care se executa operatii asupra tabelului "Client": INSERT, DELETE, SELECT*/
public class stockDAO {
    protected static final Logger LOGGER = Logger.getLogger(clientDAO.class.getName());
    /** Variabila statica initializata cu un Query de insert*/
    private static final String insertStatementString = "INSERT INTO Stock (productName,Quantity)"
            + " VALUES (?,?)";
    /**Variabila statica initializata cu un Query de delete in functie de numele produsului*/
    private static final String deleteStatementString="DELETE FROM Stock WHERE productName=?";
    /**Variabila statica initializata cu un Query de update a cantitatii cu valoarea cantitatii - o anumita valoare*/

    private static final String updateStatementString="UPDATE Stock" +" SET Quantity=Quantity-?"+" WHERE productName=?";

    /**Variabila statica initializata cu un Query de update a cantitatii cu valoarea cantitatii + o anumita valoare*/

    private static final String anotherUpdateStatementString="UPDATE Stock "+" SET Quantity=Quantity+?"+" WHERE productName=?";
    /**Variabila statica initializata cu un Query de Select *  din clasa Stock*/
    private final static String findStatementString = "SELECT * FROM Stock";


    /**
     * @param stock
     * Metoda statica in care se lucreaza cu insertStatementString-ul de insert care insereaza un rand in tabelul Stock
     * din baza de date care contine 2 valori: numele produsului si cantitatea. Se seteaza aceste doua valori prin apelarea
     * metodelor mutatoare a parametrului de tip Stock. In caz ca inserarea nu s-a putut efectua va aparea un mesaj care
     * va contine motivul pentru care nu s-a putut efectua operatia.
     */
    public static int insert(Stock stock) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, stock.getProductName());
            insertStatement.setInt(2, stock.getQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "InsertQuantity:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * @param product
     * Metoda in care se lucreaza cu deleteStatementString-ul de delete care contine un query
     * care efectueaza stergerea unui rand din tabelul Stock in functie de numele produsului.
     * Numele care trebuie sters este dat ca si paramtru al metodei.
     */
    public static int deleteStock(String product)
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
            LOGGER.log(Level.WARNING, "DeleteQuantity:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;

    }

    /**In aceasta metoda se ceeaza mai intai un ArrayList in care se pastreaza toate obiecte de tipul Stock obtinute
     * prin executarea query-ului de "SELECT * FROM Stock). Apoi se verifica daca exista un obiect de tipul stock
     * care are numele produslui egal cu string-ul produs dat ca si parametru, in cazul in care exista, se verifica daca
     * valoarea cantitatii obiectului respectiv este mai mic decat int-ul dat ca si parametru la functie, daca este
     * metoda va returna 1, altfel va returna 0.
     * @param produs
     * @param cantitate
     * @return r
     */
    public static int isValid(String produs, int cantitate)
    {
        ArrayList<Stock> stocuri=new ArrayList<Stock>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while( rs.next()) {
                String productName= rs.getString("productName");
                int cant = rs.getInt("Quantity");
                stocuri.add(new Stock(productName, cant));

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        for(Stock s:stocuri)
        {
            if(s.getProductName().equals(produs))
            {
                if(s.getQuantity()<cantitate)
                    return 1;
            }
        }
        return 0;

    }

    /**Se realizeaza un update al campului de cantitate, in functie de numele prodului
     * Aceasta metoda este folosita pentru cazul in care este solicitata o comanda si trebuie modificata valoarea cantitatii
     * actuale, mai exact se scade din aceasta valoarea cantitatii din comanda.
     * */
    public static int updateStockOrder(String produs, int cantitate)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updateStock = -1;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, cantitate);
            updateStatement.setString(2, produs);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "FirstUpdateQuantity:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updateStock;
    }

    /**Se realizeaza un update al campului de cantitate, in functie de numele prodului
     * Aceasta metoda este folosita pentru cazul in care este solicitata o alta inserare a unui produs cu acelasi nume
     * si trebuie modificata valoarea cantitatii acestuia, mai exact se aduna la aceasta valoarea cantitatii din noua inserare.
     * */
    public static int updateStockInsert(String produs, int cantitate)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updateStock = -1;
        try {
            updateStatement = dbConnection.prepareStatement(anotherUpdateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, cantitate);
            updateStatement.setString(2, produs);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "SecondUpdateQuantity:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updateStock;
    }

    /**
     * @return Se returneaza un ArrayList cu toate stocurile existente in tabelul Stock.
     * In aceasta metoda se lucreaza cu findStatementString-ul initializat cu query-ul de "SELECT * FROM Stock".
     * Obiectul rs de tip ResultSet va contine rezultatul query-ului, care se obtine prin apelul metodei executeQuery()
     * Datele sunt extrase apoi in 2 variabile, cu ajutorul carora un nou obiect de tipul Stock este creat si adaugat in
     * ArrayList */
    public static ArrayList<Stock> find() {
        ArrayList<Stock> stocuri = new ArrayList<Stock>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("productName");
                int cant = rs.getInt("Quantity");
                stocuri.add(new Stock(productName, cant));

            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:select " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
       return stocuri;
    }
}

