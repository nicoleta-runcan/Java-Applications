package Model;

/** Clasa Client este clasa care inglobeaza datele despre fiecare client*/
public class Client {
    /**Parametru care pastreaza id-ul fiecarui client*/
    private int idClient;
    /**Parametru care pastreaza numele fiecarui client*/
    private String clientName;
    /**Parametru care pastreaza adresa fiecarui client*/
    private String clientAdress;

    public Client(int id, String Name, String Adress) {

        this.idClient =id;
        this.clientName = Name;
        this.clientAdress = Adress;
    }


    /** @return valoarea parametrului idClient*/
    public int getClientID() {

        return idClient;
    }

    /** @return valoarea parametrului clientName*/
    public String getClientName() {
        return clientName;
    }

    /** @return valoarea parametrului clientAdress*/
    public String getClientAdress() {
        return clientAdress;
    }

}
