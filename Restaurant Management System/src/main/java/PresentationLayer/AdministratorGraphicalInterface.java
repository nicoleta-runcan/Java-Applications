package PresentationLayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import BusinessLayer.*;

public class AdministratorGraphicalInterface extends JFrame{

    private JPanel contentPane;
    private JTextField TypeProd=new JTextField(15);
    private JTextField name=new JTextField(15);
    private JTextField price=new JTextField(10);
    private JTextField comp=new JTextField(30);
    private JTextField newName=new JTextField(15);
    private JTextField newPrice=new JTextField(10);

    private JButton b1=new JButton("Create menu item");
    private JButton b2=new JButton("Delete menu item");
    private JButton b3=new JButton("Edit menu item");
    private JButton b4=new JButton("View MenuItems");
    private JButton clear=new JButton("Clear");

    private Restaurant rest;

    public AdministratorGraphicalInterface( Restaurant myRest)
    {
        rest=myRest;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 800, 500);
        setLocation(20, 100);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JLabel title=new JLabel("Administrator", (int) JLabel.LEFT);
        title.setFont(new Font("Serif", Font.ITALIC, 30));
        contentPane.add(title);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        JPanel panel1=new JPanel();
        panel1.add(new JLabel("Product type"));
        panel1.add(TypeProd);

        panel1.add(new JLabel("Product Name"));
        panel1.add(name);

        panel1.add(new JLabel("Price"));
        panel1.add(price);

        JPanel panel2=new JPanel();
        panel2.add(new JLabel("Component products"));
        panel2.add(comp);

        JPanel panel3=new JPanel();
        panel3.add(new JLabel("New Name"));
        panel3.add(newName);

        panel3.add(new JLabel("New Price"));
        panel3.add(newPrice);

        JPanel pButton=new JPanel() ;
        pButton.add(b1);
        pButton.add( Box.createRigidArea(new Dimension(20,20)) );
        pButton.add(b2);
        pButton.add( Box.createRigidArea(new Dimension(20,20)) );
        pButton.add(b3);
        pButton.add( Box.createRigidArea(new Dimension(20,20)) );
        pButton.add(b4);
        b1.setBackground(Color.lightGray);
        b2.setBackground(Color.lightGray);
        b3.setBackground(Color.lightGray);
        b4.setBackground(Color.lightGray);
        p1.add(panel1);
        p1.add(panel2);
        p1.add(panel3);
        p1.add(pButton);
        clear.setPreferredSize(new Dimension(100, 40));
        clear.setBackground(Color.WHITE);
        p1.add(clear);
        contentPane.add(p1);
        setContentPane(contentPane);
        contentPane.setBackground(Color.lightGray);

    }

    public void addCListener(ActionListener action) {

        b1.addActionListener(action);
    }

    public void addDListener(ActionListener action)
    {
        b2.addActionListener(action);
    }

    public void addEListener(ActionListener action) {
        b3.addActionListener(action);
    }

    public void addTListener(ActionListener action) {
        b4.addActionListener(action);
    }

    public void addClearListener(ActionListener action) {
        clear.addActionListener(action);
    }


    public String getUserInput_1() {
        return  TypeProd.getText();
    }

    public String getUserInput_2(){
        return name.getText();
    }

    public String getUserInput_3(){
        return price.getText();
    }

    public String getUserInput_4(){
        return comp.getText();
    }

    public String getUserInput_5(){
        return newName.getText();
    }

    public String getUserInput_6(){
        return newPrice.getText();
    }

    public void reset()
    {
        TypeProd.setText("");
        name.setText("");
        price.setText("");
        comp.setText("");
        newPrice.setText("");
        newName.setText("");
    }


}
