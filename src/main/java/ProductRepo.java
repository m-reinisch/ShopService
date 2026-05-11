import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Product Warehouse
 *
 * @author Michael Reinisch
 */
public class ProductRepo {
    private final Map<Integer, Product> warehouse= new HashMap<>();

    /** Stores one product
     *
     * @param product to store
     * @return true = successful, false = failed
     */
    public Boolean storeProduct(Product product){
        if(warehouse.containsKey(product.id())){
            return false;
        } else {
            warehouse.put(product.id(), product);
            return true;
        }
    }

    /** Removes one producrt
     *
     * @param productId id of the product to be removed
     * @return true = successful, false = failed
     */
    public Boolean removeProduct(Integer productId){
        if (warehouse.containsKey(productId)){
            warehouse.remove(productId);
            return true;
        } else {
            return false;
        }
    }

    /** Displays one product, if available.
     *
     * @param id of the product to be shown
     * @return formated string
     */
    public String productInquiry(Integer id){
        if (warehouse.containsKey(id)){
            return String.format(Locale.US,"%d %s %.2f", warehouse.get(id).id(), warehouse.get(id).name(), warehouse.get(id).price());
        } else {
            return "Product not available!";
        }
    }

    /** Displays all products.
     *
     * @return formated string
     */
    public String productInquiry(){
        String products= "";

        for (Integer key:warehouse.keySet()){
            products+= String.format(Locale.US,"%d %s %.2f\n", warehouse.get(key).id(), warehouse.get(key).name(), warehouse.get(key).price());
        }
        return products;
    }

    /** Finds the first product in stock.
     *
     * @param productName product to search for
     * @return id of product or 0
     */
    public Integer findByProductMame(String productName){
        for (Integer key:warehouse.keySet()){
            if (warehouse.get(key).name() == productName){
                return warehouse.get(key).id();
            }
        }
        return 0;
    }

    public Map<Integer, Product> getWarehouse() {
        return warehouse;
    }
}
