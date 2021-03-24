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


import businessLayer.BllOrder;
import Model.Order;

/** Clasa in care se creeaza un pdf cu un tabel, in care se stocheaza datele din tabela "Order" a bazei de date, in
 * momentul in care este instantiata in metoda main*/
public class PdfGeneratorOrder {
    /**Un obiect de tip BllOrder*/
    BllOrder bllOrder;

    /**Constructorul care creeaza tabelul, apeland si metodele de addTableHeader(PdfTable) si addRows(PdfTable)*/
    public PdfGeneratorOrder(String nume) throws DocumentException, IOException, URISyntaxException, SQLException {
        bllOrder=new BllOrder();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(nume));
        document.open();
        PdfPTable table = new PdfPTable(bllOrder.getNumberOfColumns());
        addTableHeader(table);
        addRows(table);
        document.add(table);
        document.close();
    }

    /**Se creeaza header-ul tabelului cu nuemele coloanelor tabelului Order din baza de date warehouse */
    private void addTableHeader(PdfPTable table) throws SQLException {
        String[] columnName=bllOrder.getColumnsNames();
        Stream.of(columnName)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }


    /**Se adauga celule in tabel cu datele fiecarei comenzi, obtinute prin apelul metodei "getOrders()". Fiecare rand va contine
     * id-ul comenzii, numele clientului, numele produsului si cantitatea comenzii*/
    private void addRows(PdfPTable table) {
        ArrayList<Order>comenzi =new ArrayList<Order>();
        comenzi =bllOrder.getOrders();
        for(Order c: comenzi)
        {
            table.addCell(String.valueOf(c.getIdOrder()));
            table.addCell(c.getClientName());
            table.addCell(c.getProductName());
            table.addCell(String.valueOf(c.getOrderQuantity()));
        }

    }

}