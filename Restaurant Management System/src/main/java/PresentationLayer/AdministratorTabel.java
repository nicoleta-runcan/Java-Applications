package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import BusinessLayer.*;
import BusinessLayer.MenuItem;

public class AdministratorTabel extends JFrame {
    JTable  table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    Restaurant rest;
    String headers[] = { "Nume produs", "Pret", "Produse Componente"};

    public AdministratorTabel(Restaurant myRest) {
        rest=myRest;
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        scroll = new JScrollPane(table);

        insert();

        add(scroll, BorderLayout.CENTER);
        setSize(700, 300);
        setVisible(true);
    }

    public void  insert()
    {
        ArrayList<String> nume=new ArrayList<String>();
        ArrayList<Double> pret=new ArrayList<Double>();
        ArrayList<String> componente=new ArrayList<String>();
        for(MenuItem item: rest.getMenuItems())
        {
            String s="";
            nume.add(item.getName());
            pret.add(item.getPrice());
            if(item instanceof CompositeProduct)
            {
                for(MenuItem i: ((CompositeProduct) item).getMenuItemsComp())
                {
                    s+=i.getName()+" ";
                }
            }
            componente.add(s);
        }

        for (int i = 0; i < nume.size(); i++) {
            model.addRow(new Object[] { String.valueOf(nume.get(i)),
                    String.valueOf(pret.get(i)), String.valueOf(componente.get(i))});
        }
    }
}
