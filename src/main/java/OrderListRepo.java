import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
        for (Order o:orderList){
            if (o.id() == order.id()){
                return false;
            }
        }
        orderList.add(order);
        return true;
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
        DateTimeFormatter german= DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        for (int i= 0; i < orderList.size(); i++){
            if (orderList.get(i).id() == id){
                return String.format(Locale.US,"%d %s %s %s %.2f %s %s",
                        orderList.get(i).id(), orderList.get(i).name(),
                        orderList.get(i).costumer(), orderList.get(i).products(),
                        orderList.get(i).totalPrice(), orderList.get(i).status(),
                        LocalDateTime.ofInstant(orderList.get(i).orderTimestamp(), ZoneId.systemDefault()).format(german));
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
        DateTimeFormatter german= DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        for (int i= 0; i < orderList.size(); i++){
            orders+= String.format(Locale.US,"%d %s %s %s %.2f %s %s\n",
                    orderList.get(i).id(), orderList.get(i).name(),
                    orderList.get(i).costumer(), orderList.get(i).products(),
                    orderList.get(i).totalPrice(), orderList.get(i).status(),
                    LocalDateTime.ofInstant(orderList.get(i).orderTimestamp(), ZoneId.systemDefault()).format(german));
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
