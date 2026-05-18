import java.util.*;

/** Order Filing Folder
 *
 * @author Michael Reinisch
 */
public class OrderListRepo implements OrderRepo {
    private final List<Order> orderList= new ArrayList<>();

    /** Adds an order
     *
     * @param order to store
     * @return true = successful, false = failed
     */
    @Override
    public Boolean addOrder(Order order){
        if (orderList.contains(order)){
            return false;
        } else {
            orderList.add(order);
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
        for (int i= 0; i < orderList.size(); i++){
            if (orderList.get(i).id() == orderId){
                orderList.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Displays one order, if available.
     *
     * @param id of the product to be shown
     * @return formated string
     */
    @Override
    public String orderInquiry(Integer id){
        for (int i= 0; i < orderList.size(); i++){
            if (orderList.get(i).id() == id){
                return String.format(Locale.US,"%d %s %s %s %.2f %s", orderList.get(i).id(), orderList.get(i).name(), orderList.get(i).costumer(), orderList.get(i).products(), orderList.get(i).totalPrice(), orderList.get(i).status());
            }
        }
        return "Order not available!";
    }

    /** Displays all orders
     *
     * @return formated string
     */
    @Override
    public String orderInquiry(){
        String orders= "";

        for (int i= 0; i < orderList.size(); i++){
            orders+= String.format(Locale.US,"%d %s %s %s %.2f %s\n", orderList.get(i).id(), orderList.get(i).name(), orderList.get(i).costumer(), orderList.get(i).products(), orderList.get(i).totalPrice(), orderList.get(i).status());
        }
        return orders;
    }

    @Override
    public Order getOrder(OrderStatus status) {
        return null;
    }

    @Override
    public Order getOrder(Integer id) {
        return null;
    }

    /** Nur zu debug Zwecken
     *
     */
    public List<Order> getOrderList() {
        return orderList;
    }
}
