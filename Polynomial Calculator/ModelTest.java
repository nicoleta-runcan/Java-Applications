import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

class ModelTest {

    @Test
    void aduna() {
        Monom m1=new Monom(3, 2);
        Monom m2=new Monom(2,1);

        Monom m3=new Monom(4, 3);
        Monom m4=new Monom(5, 1);

        Polinom p1=new Polinom();
        Polinom p2=new Polinom();

        p1.add(m1);
        p1.add(m2);

        p2.add(m3);
        p2.add(m4);

        Polinom rezultat=Model.aduna(p1, p2);
        assertEquals("+4.0X^3+3.0X^2+7.0X", rezultat.toString());
    }

    @Test
    void scade() {
        Monom m1=new Monom(3, 2);
        Monom m2=new Monom(2,1);

        Monom m3=new Monom(4, 3);
        Monom m4=new Monom(5, 1);

        Polinom p1=new Polinom();
        Polinom p2=new Polinom();

        p1.add(m1);
        p1.add(m2);

        p2.add(m3);
        p2.add(m4);

        Polinom rezultat=Model.scade(p1, p2);
        assertEquals("-4.0X^3+3.0X^2-3.0X", rezultat.toString());
    }

    @Test
    void inmulteste() {
        Polinom pol1=new Polinom("1X^4+2X^3");
        Polinom pol2=new Polinom("1X^3+2X^1");

        Polinom rezultat=Model.inmulteste(pol1, pol2);
        assertEquals("+X^7+2.0X^6+2.0X^5+4.0X^4", rezultat.toString());
    }

    @org.junit.jupiter.api.Test
    void deriveaza() {
        Polinom pol1=new Polinom("1X^4+2X^3");
        Polinom rezultat=Model.deriveaza(pol1);
        assertEquals("+4.0X^3+6.0X^2", rezultat.toString());
    }


    @Test
    void integreaza() {
        Polinom pol1=new Polinom("1X^4+2X^3");
        Polinom rezultat=Model.integreaza(pol1);
        assertEquals("+0.2X^5+0.5X^4", rezultat.toString());
    }

    @Test
    void imparte() {
        Polinom pol1=new Polinom("1X^4+2X^3");
        Polinom pol2=new Polinom("1X^3+2X^1");

        Polinom rezultat=new Polinom();
        Polinom rest=new Polinom();
        Model.imparte(rezultat, rest, pol1, pol2);
        assertEquals("+X+2.0", rezultat.toString());
        assertEquals("-2.0X^2-4.0X", rest.toString());

    }
}