package Model;

/** Aceasta clasa este utila pentru pastrarea stocului produselor*/
public class Stock {
    /**Parametru in care se pastreaza numele produsului pentru care se salveaza stocul*/
    private String productName;
    /**Parametru in care se pastreaza cantitatea fiecarui produs*/
    private int quantity;

    /**Constructor cu doi parametrii, unul de tip int si unul de tip double, prin care se initializeaza variabilele instanta*/
    public Stock(String name, int Quantity)
    {
        this.productName=name;
        this.quantity=Quantity;
    }

    /** @return valoarea parametrului quantity*/
    public int getQuantity() {
        return quantity;
    }

    /** @return valoarea parametrului productName*/
    public String getProductName() {
        return productName;
    }

}
