import java.util.*;
import java.lang.String;

public class Polinom {

    TreeSet<Monom> monoame;

    public Polinom()
    {
        monoame=new TreeSet<Monom>();
    }

    public Polinom(String s)
    {
        monoame=new TreeSet<Monom>();
        String pattern=s.replace( "-", "+-");//inlocuiesc + cu - in polinom
        String[] mon=pattern.split("[+]");//fac split dupa +
        /*for(String sir:mon) {
            System.out.println(sir);
        }*/
        for(String sir:mon) {
            if (sir.length() > 0) {
                //System.out.println(sir);
                Monom aux = new Monom(sir);
                //System.out.println(aux);
                monoame.add(aux);
            }

        }
    }


    public TreeSet<Monom> getPolinom()
    {
        return monoame;
    }

    public void add(Monom a)
    {
        monoame.add(a);

    }

    public Monom getFirst() {
        return this.getPolinom().first();
    }

    public boolean isEmpty()
    {
        return this.getPolinom().isEmpty();
    }

    public void removeA()
    {
        this.getPolinom().clear();
    }


     public String toString()
     {
         String s="";
         for(Monom m1: this.getPolinom())
         {
             s+=m1.toString();
         }
         return s;
     }


}
