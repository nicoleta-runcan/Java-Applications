package BusinessLayer;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem{
    ArrayList<MenuItem> menuItems;


    public CompositeProduct(String nume)
    {
        super(nume);
        this.menuItems=new ArrayList<MenuItem>();
    }

    public void addItem(MenuItem item)
    {
        this.menuItems.add(item);
    }

    public void computePrice()
    {
        double sum=0;
        for(MenuItem i: this.menuItems)
        {
            sum+=i.getPrice();
        }
         super.computePrice(sum);

    }

    public ArrayList<MenuItem> getMenuItemsComp()
    {
        return menuItems;
    }
}
