import java.awt.*;
import java.util.*;

public class MainClass {

    public static void main( String[] args) {

        Model model=new Model();
        View view=new View(model);
        view.setVisible(true);
        Controller controller=new Controller(model, view);

    }



}
