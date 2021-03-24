package BusinessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private double price;

    public MenuItem(String name)
    {
        this.name=name;
    }

    public MenuItem()
    {

    }

    public void computePrice(double price)
    {
        this.price=price;
    }

    public double getPrice()
    {
        return this.price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String toString()
    {
        String s="";
        s+="Nume produs "+this.getName()+" Pret:"+this.getPrice();
        return s;
    }


}
