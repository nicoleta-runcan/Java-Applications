package PresentationLayer;

import BusinessLayer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorController {
    private AdministratorGraphicalInterface a_view;
    private Restaurant myRest;

    public AdministratorController(Restaurant rest, AdministratorGraphicalInterface aview) {
        myRest = rest;
        a_view = aview;
        a_view.addCListener(new AddCMIListener());
        a_view.addDListener(new AddDListener());
        a_view.addEListener(new AddEListener());
        a_view.addClearListener(new ClearListener());
        a_view.addTListener(new AddVListener());

    }

    class AddCMIListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            String userInput3="";
            String userInput4="";
            userInput1 = a_view.getUserInput_1();
            userInput2= a_view.getUserInput_2();
            userInput3=a_view.getUserInput_3();
            userInput4=a_view.getUserInput_4();

            myRest.createMenuItem(userInput1,userInput2,userInput3,userInput4);
        }

    }

    class AddDListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput2 = "";
            userInput2 = a_view.getUserInput_2();
            myRest.deleteMenuItem(userInput2);
        }

    }

    class AddEListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput2 = "";
            String userInput5="";
            String userInput6="";
            userInput2 = a_view.getUserInput_2();
            userInput5=a_view.getUserInput_5();
            userInput6=a_view.getUserInput_6();
            myRest.editMenuItem(userInput2,userInput5,userInput6);
        }

    }

    class AddVListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
           AdministratorTabel tabel=new AdministratorTabel(myRest);
        }

    }

    class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            a_view.reset();
        }
    }

}