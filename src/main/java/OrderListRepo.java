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
    public String orderInquiry(){
        String orders= "";

        for (int i= 0; i < orderList.size(); i++){
            orders+= String.format(Locale.US,"%d %s %s %s %.2f %s\n", orderList.get(i).id(), orderList.get(i).name(), orderList.get(i).costumer(), orderList.get(i).products(), orderList.get(i).totalPrice(), orderList.get(i).status());
        }
        return orders;
    }

    /** Nur zu debug Zwecken
     *
     */
    public List<Order> getOrderList() {
        return orderList;
    }
}
