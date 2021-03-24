package BusinessLayer;

public interface IRestaurantProccesing {

    /** @pre (typeProduct.equals("Base") || typeProduct.equals("Composite"))
     *  @pre checkPrice(price)
     *  @pre !componentProduct.equals("")
     *  @post menu.size()==size;
     *  Metoda prin care se adauga cate un nou element in meniul restaurantului.
     */
    public void createMenuItem(String typeProduct, String product, String price, String componentProduct);

    /** @pre isInMenu(product) &amp;&amp; !product.equals("")
     * @param product
     * @post menu.size() smaller than size
     Metoda prin care se sterge cate un element din meniu in functie de numele acestuia.
     */
    public void deleteMenuItem(String product);

    /**
     * @param oldName
     * @param newName
     * @param newPrice
     * @pre isInMenu(oldName) &amp;&amp; !oldName.equals("")
     * @pre checkPrice(newPrice)
     * @post  assert !isInMenu(oldName) || isTheSamePrice(oldName, newPrice)
     * Metoda prin care se pot edita anumite metode din restaurant:numele unui produs, pretul acestuia sau ambele caracteristici.
     */
    public void editMenuItem(String oldName, String newName, String newPrice);

    /**
     * @pre !menu.isEmpty() &amp;&amp; !idOrder.equals("") &amp;&amp; !data.equals("") &amp;&amp; !table.equals("") &amp;&amp; !produse.equals("")
     * @post orders.size()==size || orders.size()==(size+1)
     * @param idOrder
     * @param data
     * @param table
     * @param produse
     * Metoda prin care se creeaza o noua comanda pe baza produselor existente in meniu.
     */
    public void createNewOrder(String idOrder, String data, String table, String produse);

    /** @pre !menu.isEmpty() &amp;&amp; !produse.equals("")
     * @post totalPrice greater or equal than 0;
     * @post  assert f.length()!=0;
     * @param produse
     * @return pretTotal
     * Metoda prin care se compune pretul comenzii plasate.
     */
    public double computePriceOrder(String produse);

    /**@pre !menu.isEmpty() &amp;&amp; !produse.equals("")
     * @post billPrice greater or equal than 0;
     * @param produse
     * Metoda prin care se creeaza o factura pentru comanda plasata
     */
    public void generateBill(String produse);

}
