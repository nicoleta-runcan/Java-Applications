import java.util.Random;

public class Client implements Comparable{
    private int ID;
    private int t_arrival;
    private int t_service;
    //private int waitClient=0;
    private boolean status_coada;


    public Client(int id, int t_arrival, int t_service)
    {
        this.ID=id;
        this.t_arrival=t_arrival;
        this.t_service=t_service;
    }


    public int getT_service() {
        return t_service;
    }

    public int getID() {
        return ID;
    }

    public int getT_arrival() {
        return t_arrival;
    }


    public void setT_service(int t_service) {
        this.t_service = t_service;
    }


    public String toString()
    {
        return "("+this.getID()+" "+this.getT_arrival()+" "+this.getT_service()+")";
    }

    public int compareTo(Object m)
    {
        if(this.t_arrival == ((Client)m).t_arrival)
        {
            return 0;
        }
        if(this.t_arrival<((Client)m).t_arrival)//pentru a le orona descrescator
        {
            return -1;
        }
        return 1;
    }
}
