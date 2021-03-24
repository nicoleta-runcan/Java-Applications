import java.io.*;
import java.util.*;

public class Scheduler extends Thread{
    protected int N;
    private int Q;
    private int t_simulation;
    private int t_min_arrival;
    private int t_max_arrival;
    private int t_min_simulation;
    private int t_max_simulation;
    private String path;
    ArrayList<Client> waitingClients;
    ArrayList<Coada> cozi;

    public Scheduler(int n, int q, int t_simulation, int t_min_a, int t_max_a, int t_min_s, int t_max_s, String path)  {
        this.N = n;
        this.Q=q;
        this.t_simulation=t_simulation;
        this.t_min_arrival=t_min_a;
        this.t_max_arrival=t_max_a;
        this.t_min_simulation=t_min_s;
        this.t_max_simulation=t_max_s;
        this.path=path;
        waitingClients=new ArrayList();
        for(int i=1; i<=N; i++)
        {
            int id=i;
            int arrival_T=new Random().nextInt((t_max_arrival-t_min_arrival)+1)+t_min_arrival;
            int simulation_T=new Random().nextInt((t_max_simulation-t_min_simulation)+1)+t_min_simulation;
            waitingClients.add(new Client(id, arrival_T, simulation_T));
        }
        Collections.sort(waitingClients);
        cozi=new ArrayList();
        for(int i=1; i<=Q; i++)
        {
            Coada c=new Coada();
            cozi.add(c);
            c.start();
        }
    }


    public ArrayList<Coada> getQueues()
    {
        return cozi;
    }

    public Coada getMinWaitingTime()
    {
        int waitingmin=10000000;
        Coada aux=new Coada();
        for(Coada c: this.getQueues())
        {
            if(c.getWaitingTime()<waitingmin)
            {
                waitingmin=c.getWaitingTime();
                aux=c;
            }
        }
        return aux;
    }

    public void run() {
        int currentTime=0;
        int nr_clienti=0;
        int suma=0;
        float average=0;
        PrintWriter fileWriter = null;
        try { fileWriter = new PrintWriter(path, "UTF-8");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        while(currentTime<t_simulation )
        {
            Iterator<Client> c=waitingClients.iterator();
            while(c.hasNext())
            {
                Coada coada=this.getMinWaitingTime();
                Client aux=c.next();
                if(aux.getT_arrival()==currentTime)
                {
                    coada.addClient(aux);
                    nr_clienti++;
                    c.remove();
                }
            }
            fileWriter.println("Time "+currentTime);
            fileWriter.println(this.toString());
            fileWriter.println();
            if(waitingClients.isEmpty())
            { if(isEmptyAll())
                    break;
            }
            currentTime++;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(Coada q:this.getQueues()) {
            suma+=q.getSuma(); }
        average=(float)suma/nr_clienti;
        fileWriter.println("Average waiting time "+average);
        fileWriter.close();
        for(Coada queue: this.getQueues())
        {
            queue.setVariabila(false);
        }
    }

    public boolean isEmptyAll()
    {
        boolean aux=true;
        {
            for(Coada c: this.getQueues())
            {
                if(!c.isEmptyC())
                {
                    aux=false;
                    break;
                }
            }

        }
        return aux;
    }


    public String toString()
    {
     String s="Waiting clients:";
     for(Client c: waitingClients)
     {
         s+=c.toString()+";";
     }
     int i=1;
     for(Coada c:cozi)
     {
         if(c.isEmptyC())
         {
             s+="\n"+"Queue "+i+": closed";
             i++;
         }
         else
         {
             s+="\n"+"Queue " +i+": ";
             for(Client cl:c.getClintCoada())
             {
                 s+=cl.toString()+";";
             }
             i++;
         }
     }
     return s;
    }
}
