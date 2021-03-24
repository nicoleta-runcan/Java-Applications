import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Coada extends Thread{
    private BlockingQueue<Client> clienti;
    private int waitingTime;
    private int suma;

    private boolean variabila=true;

    public Coada()
    {
        this.clienti=new LinkedBlockingQueue<>();

    }

    public void setVariabila(boolean variabila) {
        this.variabila = variabila;
    }

    public BlockingQueue<Client> getClintCoada()
    {
        return clienti;
    }

    public void addClient(Client client)
    {
        clienti.add(client);
        this.waitingTime+=client.getT_service();
    }

    public int getWaitingTime()
    {
        return this.waitingTime;
    }

    public int getSuma() {
        return suma;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void run()
    {

        while(variabila)
        {
                if(!this.isEmptyC())
                {
                    Client c=clienti.peek();
                    while(c.getT_service()>0)
                    {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.suma+=clienti.size();
                        c.setT_service(c.getT_service()-1);

                        this.waitingTime--;

                    }
                    if(c.getT_service()==0)
                    {

                        clienti.remove(c);
                    }




                }

        }
    }


    public boolean isEmptyC()
    {
        return this.clienti.isEmpty();
    }

}
