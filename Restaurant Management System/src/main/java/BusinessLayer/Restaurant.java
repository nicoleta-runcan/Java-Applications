package BusinessLayer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import DataLayer.FileWriter;
public class Restaurant implements IRestaurantProccesing, Serializable {

    private ArrayList<MenuItem> menu;
    private HashMap<Order, ArrayList<MenuItem>> orders;
    private PropertyChangeSupport changes=new PropertyChangeSupport(this);
    private String not;
    private int cont;
    private String path;

    public Restaurant(String path)
    {
        not="nu";
        this.path=path;
        this.menu=new ArrayList<MenuItem>();
        this.orders=new HashMap<Order, ArrayList<MenuItem>>();
        cont=1;
    }


    protected boolean isWellFormed()
    {
        if(menu==null)
            return false;
        for(Map.Entry<Order,ArrayList<MenuItem>> ent :this.getOrders().entrySet())
           {
               if(ent.getValue()==null)
                   return false;

           }
       return true;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public boolean checkPrice(String price)
    {
      boolean aux=true;
      for(int i=0; i<price.length(); i++)
      {
          if((price.charAt(i)>='0' && price.charAt(i)<='9')|| price.charAt(i)=='.')
          {
            aux=true;
          }
          else
          {
              aux=false;
              break;
          }
      }
      return aux;
    }

    public void createMenuItem(String typeProduct, String product, String price, String componentProduct)
    {

        assert (typeProduct.equals("Base") || typeProduct.equals("Composite")): "Nu s-au introdus corect datele";
        assert isWellFormed();
        MenuItem prod;
        int size=menu.size();

        if(typeProduct.equals("Base"))
        {
            assert this.checkPrice(price): "Nu s-a introdus pretul corect";
            Double p=Double.parseDouble(price);
            prod=new BaseProduct(product);
            prod.computePrice(p);
            menu.add(prod);
        }
        else if(typeProduct.equals("Composite"))
        {
            assert !componentProduct.equals(""):"Nu s-a introdus niciun element component";
            prod=new CompositeProduct(product);
            boolean ok=true;
            String[] produse_comp=componentProduct.split(", ");
            for(int i=0; i<produse_comp.length; i++) {
                for (MenuItem c : menu) {
                    if(c.getName().equals(produse_comp[i]))
                    {
                        ((CompositeProduct) prod).addItem(c);
                        ok=true;
                        break;
                    }
                    else{
                        ok=false;
                    }

                }
                if(ok==false)
                    return;
            }
            ((CompositeProduct) prod).computePrice();
            size=menu.size();
            menu.add(prod);

        }
        assert menu.size()==(size+1);
        assert isWellFormed();
        FileWriter.doSerialization(this.menu, path);
    }


    public MenuItem getItem(String nume)
    {
        MenuItem i=new MenuItem();
        for(MenuItem item: menu)
        {
            if(item.getName().equals(nume))
            {
                i=item;
            }
        }
        return i;
    }

    public boolean isInMenu(String product)
    {
        for(MenuItem item: menu)
        {
            if(product.equals(item.getName()))
               return true;
        }
        return false;
    }

    @Override
    public void deleteMenuItem(String product) {
        assert isInMenu(product) && !product.equals(""): "Nu a fost introdus niciun produs sau produsul nu se afla in meniu";
        assert isWellFormed();
        int size=menu.size();
        MenuItem myItem=getItem(product);
        Iterator<MenuItem> c= menu.iterator();
        ArrayList<String> aux_name =new ArrayList<String>();
        while(c.hasNext())
        {
            MenuItem item=c.next();
            if(item instanceof CompositeProduct)
            {
                CompositeProduct compo=(CompositeProduct)item;
                if(((CompositeProduct) item).getMenuItemsComp().contains(myItem))
                {
                    aux_name.add(compo.getName());
                }
            }
        }
        for(String i: aux_name)
        {
            deleteMenuItem(i);
        }

        menu.remove(myItem);
        assert menu.size()<size:"Nu s-a efectuat stergerea";
        assert isWellFormed();
        FileWriter.doSerialization(this.menu, path);
    }



    public boolean isTheSamePrice(String string, String price)
    {
        double pri=Double.parseDouble(price);
        for(MenuItem item:menu)
        {
            if(item.getName().equals(string))
            {
                if(item.getPrice()==pri)
                    return true;
            }
        }
        return false;
    }
    @Override
    public void editMenuItem(String oldName, String newName, String newPrice) {

        assert isInMenu(oldName) && !oldName.equals("") :"Produsul care se doreste a fi editat nu se afla in meniu";
        assert isWellFormed();
        if(!oldName.equals("") && !(newName.equals("")))
        {
            for(MenuItem m: menu)
            {
                if(m.getName().equals(oldName))
                   {
                       m.setName(newName);
                       if(!newPrice.equals(""))
                       {
                           assert checkPrice(newPrice):"Pretul nou nu este valid";
                           Double p=Double.parseDouble(newPrice);
                           m.computePrice(p);
                       }
                   }
                if(m instanceof CompositeProduct)
                {
                    ((CompositeProduct) m).computePrice();
                }
            }
        }
        if(!newPrice.equals(""))
        {
            assert checkPrice(newPrice):"Pretul nou nu este valid";
            for(MenuItem m:menu)
            {
                if(m instanceof BaseProduct) {
                    if (m.getName().equals(oldName)) {
                        Double p = Double.parseDouble(newPrice);
                        m.computePrice(p);
                    }
                }
            }
        }
      assert !isInMenu(oldName) || isTheSamePrice(oldName, newPrice): "Nu a fost efectuata editarea";
      assert isWellFormed();
        FileWriter.doSerialization(this.menu, path);
    }

    @Override
    public void createNewOrder(String idOrder, String data, String table, String produse) {
        assert !menu.isEmpty() && !idOrder.equals("") && !data.equals("") && !table.equals("") && !produse.equals("");
        assert isWellFormed();
        int size=orders.size();
        int IdOrder=Integer.parseInt(idOrder);
        int table_int=Integer.parseInt(table);
        Order order=new Order(IdOrder, data, table_int);
        ArrayList<MenuItem> orderProducts=new ArrayList<MenuItem>();
        String[] produseArray=produse.split(", ");
        boolean aux=false;
        for(int i=0; i<produseArray.length; i++)
        {
            for(MenuItem item: menu)
            {
                if(produseArray[i].equals(item.getName())) {
                    aux=true;
                    orderProducts.add(item);
                    if (item instanceof CompositeProduct) {
                        not="da";
                        changes.firePropertyChange(not, "nu", "da");
                        not="nu";
                    }
                }
            }
        }

        if(aux)
        {
            orders.put(order, orderProducts);
        }
        assert orders.size()==size || orders.size()==(size+1);
        assert isWellFormed();
    }

    public void addPropertyChangeListener(PropertyChangeListener l)
    {
        changes.addPropertyChangeListener(l);
    }

    @Override
    public double computePriceOrder(String produse) {
        assert !menu.isEmpty() && !produse.equals("");
        assert isWellFormed();
        String[] produseArray=produse.split(", ");
        double totalPrice=0;
        boolean aux=false;
        for(int i=0; i<produseArray.length; i++)
        {
            for(MenuItem item: menu)
            {
                if(produseArray[i].equals(item.getName()))
                {
                    aux=true;
                    totalPrice+=item.getPrice();
                }
            }
        }
        assert totalPrice>=0;
        assert isWellFormed();
        if(aux)
           return totalPrice;
        return 0;
    }


    public void generateBill(String produse)
    {
        assert !menu.isEmpty() && !produse.equals("");
        assert isWellFormed();
        double billPrice=0;
        billPrice=this.computePriceOrder(produse);
        if(billPrice!=0) {
            File f=new File("Bill"+cont+".txt");
            PrintWriter fileWriter = null;
            try {
                fileWriter = new PrintWriter(f, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            fileWriter.println("FACTURA");
            fileWriter.println();
            fileWriter.println(this.toString());
            fileWriter.println("Total: " + billPrice);
            fileWriter.close();
            assert f.length()!=0;
        }
        assert billPrice>=0;
        assert isWellFormed();
    }

    public ArrayList<MenuItem> getMenuItems()
    {
        return this.menu;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrders()
    {
        return orders;
    }

    public String toString()
    {
        String s="";
        for(Map.Entry<Order, ArrayList<MenuItem>>entry: orders.entrySet())
        {
            Order hash=entry.getKey();
            s=s+"Numar comanda: "+ hash.getOrderID()+"\n"+"Data: "+hash.getDate()+"\n";

            ArrayList<MenuItem> orders;
            orders=entry.getValue();

            for(MenuItem item: orders)
            {
                s=s+item.toString()+"\n";
            }

        }
        return s;
    }

}
