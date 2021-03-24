import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MainClass {

    public static void main(String[] args) throws Exception {
        int N=0;
        int Q=0;
        int t_simulation=0;
        int t_min_a=0;
        int t_max_a=0;
        int t_min_s=0;
        int t_max_s=0;
        String aux;
        try {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);

            sc.useDelimiter(" ");
            N=Integer.parseInt(sc.nextLine());
            Q=Integer.parseInt(sc.nextLine());
            t_simulation=Integer.parseInt(sc.nextLine());
            aux=sc.nextLine();
            String[] result=aux.split(",");
            t_min_a=Integer.parseInt(result[0]);

            t_max_a=Integer.parseInt(result[1]);


            aux=sc.nextLine();
            String[] result1=aux.split(",");
            t_min_s=Integer.parseInt(result1[0]);
            t_max_s=Integer.parseInt(result1[1]);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("error file not found");
            e.printStackTrace();
        }
        //String file_out="C:\\Users\\Nicoleta\\Desktop\\out-test-2.txt";


        Scheduler myS=new Scheduler(N, Q, t_simulation, t_min_a, t_max_a, t_min_s,t_max_s,args[1]);
        myS.start();

    }
}
