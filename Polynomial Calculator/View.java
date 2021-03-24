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

public class View extends JFrame {

    private JPanel contentPane;
    private JTextField polinom1=new JTextField(30);
    private JTextField polinom2=new JTextField(30);
    private JButton b1=new JButton("+");
    private JButton b2=new JButton("-");
    private JButton b3=new JButton("*");
    private JButton b4=new JButton("/");
    private JButton b5=new JButton("'");
    private JButton b6=new JButton("Integrate");
    private JButton b7=new JButton("Clear");
    private JTextField rezultat=new JTextField(30);
    private JTextField rest=new JTextField(30);

    private Model m_model;
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public View(Model model) {
        m_model=model;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 500,800, 500);
        setLocation(20, 100);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JLabel label1=new JLabel("Polynomial Calculator", JLabel.LEFT);
        contentPane.add(label1);
        label1.setFont(new Font("Serif", Font.ITALIC, 30));
        contentPane.add( Box.createRigidArea(new Dimension(0,15)) );
        JPanel p1=new JPanel();
        JPanel p = new JPanel();
        JPanel p2=new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        //p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel l1=new JLabel("Polynomial A");
        l1.setFont(new Font("Serif", Font.BOLD, 15));
        p.add(l1);
        addText(polinom1, p);
        JLabel l2=new JLabel("    Polynomial B");
        l2.setFont(new Font("Serif", Font.BOLD, 15));
        p.add(l2);
        addText(polinom2, p);
        contentPane.add(p);


		/*p2.add(new JLabel("Polinom B"));
		p2.add(polinom2);
		contentPane.add(p2);*/
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        b1.setBackground(Color.lightGray);
        b2.setBackground(Color.lightGray);
        b3.setBackground(Color.lightGray);
        b4.setBackground(Color.lightGray);
        b5.setBackground(Color.lightGray);
        b6.setBackground(Color.lightGray);
        p1.add(b1);
        p1.add( Box.createRigidArea(new Dimension(0,10)) );
        p1.add(b2);
        p1.add( Box.createRigidArea(new Dimension(0,10)) );
        p1.add(b3);
        p1.add( Box.createRigidArea(new Dimension(0,10)) );
        p1.add(b4);
        p1.add( Box.createRigidArea(new Dimension(0,10)) );
        p1.add(b5);
        p1.add( Box.createRigidArea(new Dimension(0,10)) );
        p1.add(b6);
        //contentPane.add(p1);
        p.add( Box.createRigidArea(new Dimension(0,50)) );
        p.add(p1);
        //p.add( Box.createRigidArea(new Dimension(0,200)) );
        JLabel l3=new JLabel("Result");
        l3.setFont(new Font("Serif", Font.BOLD, 17));
        p2.add(l3);
        p2.add(rezultat);
        JLabel l4=new JLabel("    Rest:");
        l4.setFont(new Font("Serif", Font.BOLD, 17));
        p2.add(l4);
        p2.add(rest);

        b7.setBackground(Color.WHITE);
        p2.add(b7);
        contentPane.add(p);
        contentPane.add(p2);
        p.setBackground(Color.gray);
        p1.setBackground(Color.gray);
        p2.setBackground(Color.gray);
        contentPane.setBackground(Color.lightGray);


    }


    private static void addText(JTextField text, Container container)
    {
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        container.add(text);
    }

    public void addAddListener(ActionListener action) {

        b1.addActionListener(action);
    }

    public void addSListener(ActionListener action)
    {
        b2.addActionListener(action);
    }

    public void addMulListener(ActionListener action) {
        b3.addActionListener(action);
    }

    public void addDivListener(ActionListener action) {
        b4.addActionListener(action);
    }

    public void addDerivListener(ActionListener action) {
        b5.addActionListener(action);
    }

    public void addIntListener(ActionListener action) {
        b6.addActionListener(action);
    }

    public void addClearListener(ActionListener action) {
        b7.addActionListener(action);
    }

    public String getUserInput_1() {
        return polinom1.getText();
    }

    public String getUserInput_2(){
        return polinom2.getText();
    }

    public void setRezultat(Polinom newTotal) {
        rezultat.setText(newTotal.toString());
    }

    public void setRest(Polinom newTotal) {
        rest.setText(newTotal.toString());
    }

   public void reset()
    {
        rezultat.setText("");
        rest.setText("");
        polinom1.setText("");
        polinom2.setText("");
    }

}

