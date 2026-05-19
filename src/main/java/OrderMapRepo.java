import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Order Filing Folder
 *
 * @author Michael Reinisch
 */
public class OrderMapRepo implements OrderRepo {
    private final Map<Integer, Order> orderMap= new HashMap<>();

    /** Adds an order
     *
     * @param order to store
     * @return true = successful, false = failed
     */
    @Override
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
    @Override
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
    @Override
    public String orderInquiry(Integer id){
        DateTimeFormatter german= DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        if (orderMap.containsKey(id)){
            return String.format(Locale.US,"%d %s %s %s %.2f %s %s",
                    orderMap.get(id).id(), orderMap.get(id).name(),
                    orderMap.get(id).costumer(), orderMap.get(id).products(),
                    orderMap.get(id).totalPrice(), orderMap.get(id).status(),
                    LocalDateTime.ofInstant(orderMap.get(id).orderTimestamp(), ZoneId.systemDefault()).format(german));
        } else {
            return "Order not available!";
        }
    }

    /** Displays all orders
     *
     * @return formated string
     */
    @Override
    public String orderInquiry(){
        String orders= "";
        DateTimeFormatter german= DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        for (Integer key:orderMap.keySet()){
            orders+= String.format(Locale.US,"%d %s %s %s %.2f %s %s\n",
                    orderMap.get(key).id(), orderMap.get(key).name(),
                    orderMap.get(key).costumer(), orderMap.get(key).products(),
                    orderMap.get(key).totalPrice(), orderMap.get(key).status(),
                    LocalDateTime.ofInstant(orderMap.get(key).orderTimestamp(), ZoneId.systemDefault()).format(german));
        }
        return orders;
    }

    /** Find Orders by Status
     *
     * @param status being searched for
     * @return order or null
     */
    @Override
    public Order getOrder(OrderStatus status){
        for (Order o:orderMap.values()){
            if (o.status() == status){
                return o;
            }
        }
        return null;
    }

    /** Find Orders by ID
     *
     * @param id being searched for
     * @return order or null
     */
    @Override
    public Order getOrder(Integer id){
        for (Order o:orderMap.values()){
            if (o.id() == id){
                return o;
            }
        }
        return null;
    }

    /** For testing purposes only
     *
     * @return complete map
     */
    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }
}
