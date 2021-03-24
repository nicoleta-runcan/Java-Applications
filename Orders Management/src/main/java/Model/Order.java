package Model;
/** Clasa Order are 4 parametrii si la nivel de metode, doar metode mutatoare(getters)*/
public class Order {
    /** Pastreaza id-ul fiecarei comenzi*/
    private int idOrder;
    /**Pastreaza numele fiecarui client care a solicitat comanda*/
    private String clientName;
    /**Pastreaza numele produsului din comanda*/
    private String productName;
    /**Pastreaza valoarea cantitatii produsului comandat*/
    private int orderQuantity;


    public Order(int idOrder, String clientName, String productName, int orderQuantity)
    {
        this.idOrder=idOrder;
        this.clientName=clientName;
        this.productName=productName;
        this.orderQuantity=orderQuantity;
    }

    /** @return valoarea parametrului idOrder*/
    public int getIdOrder() {
        return idOrder;
    }

    /** @return valoarea parametrului clientName*/
    public String getClientName() {
        return clientName;
    }

    /** @return valoarea parametrului productName*/
    public String getProductName() {
        return productName;
    }

    /** @return valoarea parametrului orderQuantity*/
    public int getOrderQuantity() {
        return orderQuantity;
    }

}
