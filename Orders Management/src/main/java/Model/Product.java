package Model;

/** Clasa order are doua variabile instanta in care se pastreaza anumie date despre produse si la nivel de metode, goar getter*/
public class Product {

    /** Parametru in care se pastreaza numele produsului*/
    private String productName;
    /**Parametru in care sa pastreaza valoarea pretului produsului*/
    private double productPrice;


    /**Constructor cu doi parametrii, unul de tip int si unul de tip double, prin care se initializeaza variabilele instanta*/
    public Product( String productName, double productPrice)
    {
        this.productName = productName;
        this.productPrice= productPrice;
    }

    /** @return valoarea parametrului productName*/
    public String getName_product() {
        return productName;
    }

    /** @return valoarea parametrului price*/
    public double getPrice() {
        return productPrice;
    }

}
