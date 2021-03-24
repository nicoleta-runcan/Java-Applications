package PresentationLayer;

import BusinessLayer.Restaurant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaiterController {
    private WaiterGraphicalInterface w_view;
    private Restaurant myRest;

    public WaiterController(Restaurant rest, WaiterGraphicalInterface wview) {
        myRest = rest;
        w_view = wview;
        w_view.addNListener(new AddOListener());
        w_view.addPListener(new AddPListener());
        w_view.addBListener(new AddBListener());
        w_view.addClearListener(new ClearListener());
        w_view.addTListener(new AddTListener());

    }

    class AddOListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput1 = "";
            String userInput2="";
            String userInput3="";
            String userInput4="";
            userInput1 = w_view.getUserInput_1();
            userInput2= w_view.getUserInput_2();
            userInput3=w_view.getUserInput_3();
            userInput4=w_view.getUserInput_4();

            myRest.createNewOrder(userInput1,userInput2,userInput3,userInput4);
        }

    }

    class AddPListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput4="";
            userInput4=w_view.getUserInput_4();

            double price=myRest.computePriceOrder(userInput4);

            w_view.setPrice(price);

        }

    }

    class AddBListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String userInput4="";
            userInput4=w_view.getUserInput_4();

            myRest.generateBill(userInput4);
        }

    }

    class AddTListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            WaiterTabel wait=new WaiterTabel(myRest);
        }

    }

    class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            w_view.reset();
        }
    }




}
