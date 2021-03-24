package Presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

import businessLayer.BllProduct;
import businessLayer.BllStock;
import Model.Product;
import Model.Stock;

/** Clasa in care se creeaza un pdf cu un tabel, in care se stocheaza datele din tabela "Product" si "Stock" a bazei de date, in
 * momentul in care este instantiata in metoda main*/
public class PdfGeneratorProd {
    /**un obiect de tipul BllProduct*/
    BllProduct productbll;
    /**un obicet de tipul BllStock*/
    BllStock stocuribll;

    /**Constructorul care creeaza tabelul, apeland si metodele de addTableHeader(PdfTable) si addRows(PdfTable)*/
    public PdfGeneratorProd(String nume) throws DocumentException, IOException, URISyntaxException, SQLException {
        productbll=new BllProduct();
        stocuribll=new BllStock();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nume));
        document.open();
        PdfPTable table = new PdfPTable(productbll.getNumberOfColumns()+1);
        addTableHeader(table);
        addRows(table);
        document.add(table);
        document.close();
    }

    /**Se creeaza header-ul tabelului cu nuemele coloanelor tabelului Product din baza de date warehouse mai adaugandu-se
     * un nume(Stock) si pentru coloana de Quantity din tabela Stock*/
    private void addTableHeader(PdfPTable table) throws SQLException {
        String[] columnName=productbll.getColumnsNames();
        columnName[productbll.getNumberOfColumns()]="Stock";
        Stream.of(columnName)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    /**Se adauga celule in tabel cu datele fiecarei comenzi, obtinute prin apelul metodei "getProducts()" si "GetStocks()".
     * Fiecare rand va contine numele produsului, pretul, cantitatea stocata in tabelul Stock*/
   private void addRows(PdfPTable table) {
        ArrayList<Product>produse =new ArrayList<Product>();
        ArrayList<Stock> stocuri=new ArrayList<Stock>();
        produse =productbll.getProducts();
        stocuri=stocuribll.getStocks();
        for(Product p: produse)
        {
            table.addCell(p.getName_product());
            table.addCell(String.valueOf(p.getPrice()));
            for(Stock s:stocuri)
            {
                if(p.getName_product().equals(s.getProductName()))
                {
                    table.addCell(String.valueOf(s.getQuantity()));
                }
            }
        }

    }

}