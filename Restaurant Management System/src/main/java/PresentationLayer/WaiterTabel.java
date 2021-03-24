package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import BusinessLayer.*;
public class WaiterTabel extends JFrame {
        JTable  table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        JScrollPane scroll;
        Restaurant rest;
        String headers[] = { "Id", "Produse comandate"};

        public WaiterTabel(Restaurant myRest) {
            rest=myRest;
            model.setColumnIdentifiers(headers);
            table.setModel(model);
            scroll = new JScrollPane(table);

            insert();

            add(scroll, BorderLayout.CENTER);
            setSize(500, 300);
            setVisible(true);
        }

        public void insert()
        {
            ArrayList<String> vect=new ArrayList<String>();
            ArrayList<Integer> id=new ArrayList<Integer>();
            for(Map.Entry<Order,ArrayList<MenuItem>> ent:rest.getOrders().entrySet())
            {
                ArrayList<MenuItem> orderC;
                orderC=ent.getValue();
                String s="";
                for(MenuItem item:orderC)
                {
                    s+=item.getName()+", ";
                }
                vect.add(s);
                id.add(ent.getKey().getOrderID());
            }
            for(int i=0; i<vect.size(); i++)
                model.addRow(new Object[]{String.valueOf(id.get(i)), String.valueOf(vect.get(i))});
        }


}
