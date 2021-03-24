package Start;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.stream.Stream;

import Presentation.*;
import businessLayer.*;
import Model.*;

/**Aceasta clasa detine metoda Main(String[]), care incheaga toata aplicatia*/
public class Start {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     * @throws DocumentException
     * @throws URISyntaxException
      Realizeaza citirea comenzilor din fisier, iar in functie de fiecare comanda, se apeleaza metoda sau metodele potrivite
     pentru esecutarea comenzii respective. Datele din comenzi sunt separate prin folosirea split-ului, iar daca este nevoie, se
     face de asemenea si conversia unora dintre ele la tipul int. Practic in aceasta metoda se face parsarea, si inchegarea dintre
     fisierul de citire, logica din spatele aplicatiei si pdf-urile care vor contine rezultatele succesiv ale comenzilor.
     */
    public static void main(String[] args) throws SQLException, IOException, DocumentException, URISyntaxException {

       File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        String comanda;
        String[] rezultat,rezultat2;
        int indiceClient = 1, indiceOrder=1;
        int orderQuantity=0, cantitate=0;
        double pret=0;
        int indexReportClient=1, indexBill=1, indexReportOrder=1, indexReportProduct=1;
        BllClient clientBLL=new BllClient();
        BllProduct productBll=new BllProduct();
        BllOrder orderBll=new BllOrder();
        BllStock stockBll=new BllStock();
        while(sc.hasNextLine()) {
            comanda=sc.nextLine();
            rezultat = comanda.split(": ");
            if (rezultat[0].equals("Insert client")) {
                rezultat2 = rezultat[1].split(", ");
                clientBLL.addClient(new Client(indiceClient++, rezultat2[0], rezultat2[1]));
            }
            else if(rezultat[0].equals("Delete client"))
            {
                ArrayList<Order> comenzi=new ArrayList<Order>();
                comenzi=orderBll.getOrders();
                rezultat2=rezultat[1].split(", ");
                for(Order o:comenzi)
                {
                    if(o.getClientName().equals(rezultat2[0]))
                    {
                      orderBll.deleteOrderClient(o.getClientName());
                    }
                }
                clientBLL.deleteClient(rezultat2[0]);
            }
            else if(rezultat[0].equals("Insert product")){
                rezultat2 = rezultat[1].split(", ");
                pret=Double.parseDouble(rezultat2[2]);
                cantitate=Integer.parseInt(rezultat2[1]);
                ArrayList<Product> produse=new ArrayList<Product>();
                produse=productBll.getProducts();
                int ok=0;
                for(Product p:produse )
                {
                    if (p.getName_product().equals(rezultat2[0])) {
                        ok=1;
                        break;
                    }
                }
                if(ok==1)
                {
                    stockBll.updateStockInsert(rezultat2[0], cantitate);
                }
                else
                {
                    productBll.insertProduct(new Product( rezultat2[0], pret));
                    stockBll.insertStock(new Stock(rezultat2[0], cantitate));
                }
            }
            else if(rezultat[0].equals("Order"))
            {
                rezultat2=rezultat[1].split(", ");
                orderQuantity=Integer.parseInt(rezultat2[2]);
                int ok=stockBll.isValid(rezultat2[1], orderQuantity);
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("Bill"+indexBill+++".pdf"));
                Chunk chunk=new Chunk();
                document.open();
                Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

                if(ok==0)
                {
                    ArrayList<Product> produse=productBll.getProducts();
                    for(Product p:produse) {
                        if (rezultat2[1].equals(p.getName_product())) {
                            orderBll.insertOrder(new Order(indiceOrder++, rezultat2[0], rezultat2[1], orderQuantity));
                            stockBll.updateStockOrder(rezultat2[1], orderQuantity);
                            chunk = new Chunk("NUME: " + rezultat2[0] + " PRODUS: " + rezultat2[1] + " TOTAL: " + p.getPrice()*orderQuantity, font);
                        }
                    }
                }
                else
                {
                    chunk = new Chunk("Produsul "+rezultat2[1]+" nu mai este in stoc!");
                }
                document.add(chunk);
                document.close();
            }
            else if(rezultat[0].equals("Delete Product"))
            {
                ArrayList<Order> comenzi=new ArrayList<Order>();
                comenzi=orderBll.getOrders();
                for(Order o:comenzi)
                {
                    if(o.getProductName().equals(rezultat[1]))
                    {
                        orderBll.deleteOrderProduct(o.getProductName());
                    }
                }
                stockBll.deleteStock(rezultat[1]);
                productBll.deleteProduct(rezultat[1]);
            }
            else if(comanda.equals("Report client"))
            {
                PdfGeneratorClient P=new PdfGeneratorClient("ReportClient"+indexReportClient+++".pdf");
            }
            else if(comanda.equals("Report order"))
            {
                PdfGeneratorOrder P=new PdfGeneratorOrder("ReportOrder"+indexReportOrder+++".pdf");
            }
            else if(comanda.equals("Report product"))
            {
                PdfGeneratorProd P=new PdfGeneratorProd("ReportProduct"+indexReportProduct+++".pdf");
            }
        }
    }
}
