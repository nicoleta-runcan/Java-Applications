public class Model {
    public static Polinom aduna(Polinom pol1, Polinom pol2)
    {
        Polinom rezultat=new Polinom();
        Monom rezultatM=new Monom();
        for(Monom mon1 : pol1.getPolinom())
            for(Monom mon2 : pol2.getPolinom())
            {
                if(mon1.getExp()==mon2.getExp())
                {
                    rezultatM=mon1.aduna(mon2);
                    rezultat.add(rezultatM);
                }
            }

        for(Monom mon1: pol1.getPolinom())//parcurg inca o data primul polinom
        {
            if(mon1.getParc()==false)//verific daca monoamele au fost parcurse sau nu
                rezultat.add(mon1);//le adaug pe cele care nu au mai fost parcurse

        }
        for(Monom mon2 : pol2.getPolinom())
        {
            if(mon2.getParc()==false)
                rezultat.add(mon2);
        }


        return rezultat;

    }



    public static Polinom inmulteste(Polinom polinom1, Polinom polinom2)
    {
        Polinom rezultat2 = new Polinom();
        for (Monom mon1 : polinom1.getPolinom()) {
            for (Monom mon2 : polinom2.getPolinom()) {
                for (Monom mon3 : rezultat2.getPolinom()) {
                    if (mon1.inmulteste(mon2).getExp() == mon3.getExp()) {
                        mon3.setCoef(mon3.getCoef() + mon1.inmulteste(mon2).getExp());
                    }
                }
                rezultat2.add(mon1.inmulteste(mon2));
            }
        }
        return rezultat2;
    }

    public static Polinom deriveaza(Polinom p)
    {
        Polinom rez=new Polinom();
        for(Monom m : p.getPolinom())
        {
            if(m.getCoef()!=0 || m.getExp()!=0)
            {
                rez.add(m.deriveaza());
            }

        }
        return rez;
    }


    public static Polinom scade(Polinom polinom1, Polinom polinom2) {
        Polinom rezultat1 = new Polinom();
        for (Monom mon1 : polinom1.getPolinom())
            for (Monom mon2 : polinom2.getPolinom()) {
                if(mon1.getExp()==mon2.getExp())
                    rezultat1.add(mon1.scade(mon2));

            }

        for (Monom mono1 : polinom1.getPolinom()) {
            if (mono1.getParc() == false)
                rezultat1.add(mono1);

        }
        for (Monom mono2 : polinom2.getPolinom()) {
            if (mono2.getParc() == false) {
                Monom newMon=new Monom();//fac cate o copie pentru fiecare monom din cel de-al doilea polinom
                newMon.setCoef(0-mono2.getCoef());//ii setez coeficientul negativ
                newMon.setExp(mono2.getExp());
                rezultat1.add(newMon);
            }
        }

        Polinom rezultat_f=new Polinom();
        for(Monom temp: rezultat1.getPolinom())
        {
            if(temp.getCoef()!=0)//elimin elemetele din rezultat care au coeficientul 0, utila pentru impartire
            {
                rezultat_f.add(temp);
            }
        }

        return rezultat_f;
    }

    public static Polinom integreaza(Polinom p)
    {
        Polinom rez=new Polinom();
        for(Monom m : p.getPolinom())
        {
            rez.add(m.integreaza());
        }
        return rez;
    }

    public static void imparte(Polinom q, Polinom r, Polinom a, Polinom b)
    {
     Polinom aux=new Polinom();
     for(Monom mon:a.getPolinom())//fac o copie a polinomului a
     {
         aux.add(mon);
     }
     Polinom pol=new Polinom();
     while(aux.getFirst().getExp() >= b.getFirst().getExp())//conditie de oprire
     {
         pol.add((aux.getFirst()).imparte(b.getFirst()));
         q.add(pol.getFirst());
         aux=scade(aux, inmulteste(pol, b));

         pol.removeA();

     }
        for (Monom m: aux.getPolinom())
        {
            r.add(m);
        }

    }

}


