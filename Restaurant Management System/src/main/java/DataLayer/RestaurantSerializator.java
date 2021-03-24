package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class RestaurantSerializator {

    public static void doDeserializer(Restaurant rest, String path)
    {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<MenuItem> items = (ArrayList<MenuItem>) in.readObject();
            rest.setMenu(items);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }
}
