package BusinessLayer;

import javax.xml.crypto.Data;
import java.util.Objects;

public class Order {
    private int orderID;
    private String date;
    private int table;

    public Order(int orderID, String date, int table)
    {
        this.orderID=orderID;
        this.date=date;
        this.table=table;
    }

    public int getOrderID()
    {
        return this.orderID;
    }

    public int getTable()
    {
        return this.table;
    }

    public String getDate()
    {
        return this.date;
    }

    public int hashCode()
    {
       return Objects.hash(this.orderID, this.date, this.table);
    }

    public boolean equals(Object obj)
    {
       if(this==obj)
           return true;
       if(obj==null)
           return false;
       if(this.getClass() != obj.getClass())
           return false;
       Order order=(Order) obj;
       return orderID!=order.orderID && !date.equals(order.date) && table!=order.table;
    }

}
