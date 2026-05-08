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
     */
    public Boolean storeProduct(Product product){
        if(warehouse.containsKey(product.id())){
            return false;
        } else {
            warehouse.put(product.id(), product);
            return true;
        }
    }

    public Map<Integer, Product> getWarehouse() {
        return warehouse;
    }
}
