import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Order Filing Folder
 *
 * @author Michael Reinisch
 */
public class OrderListRepo implements OrderRepo {
    private final Map<Integer, Order> orderMap= new HashMap<>();

    /** Adds an order
     *
     * @param order to store
     * @return true = successful, false = failed
     */
    public Boolean addOrder(Order order){
        if (orderMap.containsKey(order.id())){
            return false;
        } else {
            orderMap.put(order.id(), order);
            return true;
        }
    }

    /** Deletes order
     *
     * @param orderId id of the order to be removed
     * @return true = successful, false = failed
     */
    public Boolean removeOrder(Integer orderId){
        if (orderMap.containsKey(orderId)){
            orderMap.remove(orderId);
            return true;
        } else {
            return false;
        }
    }

    /** Displays one order, if available.
     *
     * @param id of the product to be shown
     * @return formated string
     */
    public String orderInquiry(Integer id){
        if (orderMap.containsKey(id)){
            return String.format(Locale.US,"%d %s %s %s", orderMap.get(id).id(), orderMap.get(id).name(), orderMap.get(id).costumer(), orderMap.get(id).products());
        } else {
            return "Order not available!";
        }
    }

    /** Displays all orders
     *
     * @return formated string
     */
    public String orderInquiry(){
        String orders= "";

        for (Integer key:orderMap.keySet()){
            orders+= String.format(Locale.US,"%d %s %s %s\n", orderMap.get(key).id(), orderMap.get(key).name(), orderMap.get(key).costumer(), orderMap.get(key).products());
        }
        return orders;
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }
}
