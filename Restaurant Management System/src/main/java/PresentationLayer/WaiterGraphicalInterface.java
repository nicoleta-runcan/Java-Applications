package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import BusinessLayer.*;
public class WaiterGraphicalInterface extends JFrame{

    private JPanel contentPane;
    private JTextField orderID=new JTextField(6);
    private JTextField orderDate=new JTextField(15);
    private JTextField Table=new JTextField(10);
    private JTextField products=new JTextField(30);
    private JButton b1=new JButton("Add New Order");
    private JButton b2=new JButton("Compute Price");
    private JButton b3=new JButton("Generate Bill");
    private JButton b4=new JButton("View Orders");
    private JButton clear=new JButton("Clear");
    private JTextField price=new JTextField(20);

    Restaurant rest;
    public WaiterGraphicalInterface(Restaurant myRest) {

        rest=myRest;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500, 800, 500);
        setLocation(20, 100);
        contentPane=new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));


        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JLabel title=new JLabel("Waiter", (int) JLabel.LEFT_ALIGNMENT);
        title.setFont(new Font("Serif", Font.ITALIC, 30));
        contentPane.add(title);


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        JPanel panel1=new JPanel();
        panel1.add(new JLabel("Order ID", (int) JLabel.LEFT));
        panel1.add(orderID);

        JPanel panel2=new JPanel();
        panel2.add(new JLabel("Order Date"), orderDate);
        panel2.add(orderDate);

        JPanel panel3=new JPanel();
        panel3.add(new JLabel("Table"), Table);
        panel3.add(Table);

        JPanel p2=new JPanel();
        p2.add(new JLabel("Order Products"));
        p2.add(products);
        //p2.add( Box.createRigidArea(new Dimension(10,10)) );

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


        JPanel finalPanel=new JPanel();
        finalPanel.add(new JLabel("Total Price"));
        finalPanel.add(price);

        p1.add(panel1);
        p1.add(panel2);
        p1.add(panel3);
        p1.add(p2);
        p1.add(pButton);
        p1.add(finalPanel);
        clear.setPreferredSize(new Dimension(100, 40));
        clear.setBackground(Color.WHITE);
        p1.add(clear);



        contentPane.add(p1);

        setContentPane(contentPane);
        contentPane.setBackground(Color.lightGray);


    }


    public void addNListener(ActionListener action) {

        b1.addActionListener(action);
    }

    public void addPListener(ActionListener action)
    {
        b2.addActionListener(action);
    }

    public void addBListener(ActionListener action) {
        b3.addActionListener(action);
    }

    public void addTListener(ActionListener action) {
        b4.addActionListener(action);
    }

    public void addClearListener(ActionListener action) {
        clear.addActionListener(action);
    }


    public String getUserInput_1() {
        return  orderID.getText();
    }

    public String getUserInput_2(){
        return orderDate.getText();
    }

    public String getUserInput_3(){
        return Table.getText();
    }

    public String getUserInput_4(){
        return products.getText();
    }

    public void setPrice(double totalPrice) {
        price.setText(String.valueOf(totalPrice));
    }


    public void reset()
    {
        orderID.setText("");
        orderDate.setText("");
        Table.setText("");
        products.setText("");
        price.setText("");
    }




}

