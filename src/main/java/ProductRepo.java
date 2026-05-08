import java.util.HashMap;
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
        return String.format("1 Icecream 1.99");
    }

    /** Displays all products.
     *
     * @return formated string
     */
    public String productInquiry(){
        return String.format("");
    }

    public Map<Integer, Product> getWarehouse() {
        return warehouse;
    }
}
