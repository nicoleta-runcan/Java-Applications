package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import BusinessLayer.*;

public class ChefGraphicalInterface extends JFrame implements PropertyChangeListener {
    private JPanel contentPane;
    private TextArea text=new TextArea();

    private Restaurant myRest;
    private int cont;
    public ChefGraphicalInterface(Restaurant rest)
    {
        myRest=rest;
        cont=1;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 500, 300);
        setLocation(20, 100);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JLabel title=new JLabel("Chef", JLabel.LEFT);
        title.setFont(new Font("Serif", Font.ITALIC, 20));

        contentPane.add(title);
        contentPane.add(text);
        setContentPane(contentPane);
        contentPane.setBackground(Color.lightGray);
        rest.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        text.setText(cont++ +". A fost introdusa o comanda care contine un produs compus");
    }
}
