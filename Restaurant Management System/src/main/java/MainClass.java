import BusinessLayer.*;
import BusinessLayer.MenuItem;
import DataLayer.RestaurantSerializator;
import PresentationLayer.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainClass {

    public static void main( String[] args) throws IOException {


        Restaurant rest=new Restaurant(args[0]);
        RestaurantSerializator.doDeserializer(rest, args[0]);
        AdministratorGraphicalInterface a_view=new AdministratorGraphicalInterface(rest);
        AdministratorController a_controller=new AdministratorController(rest, a_view);
        a_view.setVisible(true);

        WaiterGraphicalInterface w_view=new WaiterGraphicalInterface(rest);
        WaiterController w_controller=new WaiterController(rest, w_view);
        w_view.setVisible(true);

        ChefGraphicalInterface c_view=new ChefGraphicalInterface(rest);
        c_view.setVisible(true);
    }
}
