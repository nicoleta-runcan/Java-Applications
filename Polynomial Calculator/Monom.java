import java.lang.management.MonitorInfo;
import java.util.*;
import java.lang.*;


public class Monom implements Comparable{
    private double coef;
    private int exp;
    private boolean parc;


    public Monom()
    {

    }

    public Monom(String s)
    {
        String p=s;
        String vect[]=s.split("[X^]");
        this.coef=Double.parseDouble(vect[0]);//pe pozitia zero din array-ul vect se gaseste coeficientul
        this.exp=Integer.parseInt(vect[2]);//pe pozitia doi se gaseste exponentul
    }


    public Monom(double coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public double getCoef() {
        return this.coef;
    }

    public int getExp()
    {
        return this.exp;
    }
    public void setExp(int exp)
    {
        this.exp=exp;
    }

    public boolean setParc(boolean s)
    {
        return this.parc=s;
    }

    public boolean getParc()
    {
        return this.parc;
    }

    public String toString() {
        if (coef == 0) {
            return "";
        }
        String s = "";
        if (coef > 0 && coef!=1) {
            if (exp == 0)
                s += "+" + this.getCoef();
            else if (exp == 1)
                s += "+" + this.getCoef() + "X";
            else if (exp > 1)
                s += "+" + this.getCoef() + "X" + "^" + exp;
        }
        else if(coef==1)
        {
            if (exp == 0)
                s += "+1" ;
            else if (exp == 1)
                s += "+X";
            else if (exp > 1)
                s += "+X^" + exp;

        }
        else {
            if (exp == 0)
                s += this.getCoef();
            else if (exp == 1)
                s += this.getCoef() + "X";
            else if (exp > 1)
                s += this.getCoef() + "X" + "^" + exp;
        }
        return s;
    }
      public Monom aduna(Monom m1)
        {
            Monom rezultat=new Monom();
            if(this.getExp()==m1.getExp())
            {
                rezultat=new Monom(this.getCoef()+m1.getCoef(), m1.getExp());
                this.setParc(true);//setez monomoale care au exponent comun ca fiind parcurse, dupa adunarea lor
                 m1.setParc(true);
            }
            return rezultat;
        }

        public Monom scade(Monom m1)
        {
            Monom rezultat=new Monom();
            if(this.getExp()==m1.getExp())
            {
                rezultat=new Monom(this.getCoef()-m1.getCoef(), this.getExp());
                this.setParc(true);
                m1.setParc(true);
            }
            return rezultat;

        }

        public Monom inmulteste(Monom m1)
        {
            Monom rezultat=new Monom();
            rezultat=new Monom(this.getCoef()*m1.getCoef(), this.getExp()+m1.getExp());
            return rezultat;
        }

        public Monom deriveaza()
        {
            Monom rezultat=new Monom();

               rezultat.setCoef(this.getCoef()*this.getExp());
               rezultat.setExp(this.getExp()-1);
           return rezultat;
        }

        public Monom integreaza()
        {
            Monom rezultat=new Monom();
            rezultat=new Monom(this.getCoef()/(this.getExp()+1), this.getExp()+1 );
            return rezultat;
        }

        public Monom imparte(Monom a)
        {
            Monom rezultat=new Monom(this.getCoef()/a.getCoef(), this.getExp()-a.getExp());
            return rezultat;
        }


        public int compareTo(Object m)
        {
            if(this.exp == ((Monom)m).exp)
            {
                return 0;
            }
            if(this.exp>((Monom)m).exp)//pentru a le orona descrescator
            {
                return -1;
            }
            return 1;
        }





    }
