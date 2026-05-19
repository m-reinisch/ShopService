import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/** A service through which we can place new orders.
 *
 * @author Michael Reinisch
 */
@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo warehouse;
    private final OrderRepo orderRepo;
    private static Integer orderNumber= 0;

    /** Order
     *
     * @param products List of Products to Order
     * @throws ProductOutOfStockException whem one product of list not found
     */
    public Integer customerOrder(String customerName, List<Product> products) throws ProductOutOfStockException {
        List<Product> orderList= new ArrayList<>();
        Double totalPrice= 0.0;

        for (int n= 0; n < products.size(); n++){
            if (warehouse.findByProductMame(products.get(n).name()) == 0){
                throw new ProductOutOfStockException(String.format("Das Produkt %s ist nicht auf Lager!", products.get(n).name()));
            } else {
                orderList.add(products.get(n));
                totalPrice= totalPrice + products.get(n).price();
            }
        }
        Order order= new Order(++orderNumber, "Custom Order", customerName, orderList, totalPrice, OrderStatus.PROCESSING, Instant.now());
        orderRepo.addOrder(order);
        return orderNumber;
    }

    /** Displays the customer's first order.
     *
     * @param orderId to search for
     * @return order or not found
     */
    public  String showOrder(Integer orderId){
        if (orderRepo.orderInquiry(orderId) == "Order not available!") {
            return "Order not found!";
        } else {
            return orderRepo.orderInquiry(orderId);
        }
    }

    /** Finds orders with a given status.
     *
     * @param status looking for
     * @return stream of found orders
     */
    public Stream<Order> listOrders(OrderStatus status){
        return orderRepo.getOrder(status).stream();
    }

    /** The requested order is being updated.
     *
     * @param orderId to search for
     * @param orderStatus new status
     * @return true = updated, false = error occurred
     */
    public Boolean updateOrder(Integer orderId, OrderStatus orderStatus){
        Order tepOrder= null;

        if (orderRepo.getOrder(orderId) != null){
            tepOrder= orderRepo.getOrder(orderId).withStatus(orderStatus);
            orderRepo.removeOrder(orderId);
            orderRepo.addOrder(tepOrder);
            return true;
        } else {
            return false;
        }
    }

    /** Finds orders with a given status by timestamp.
     *
     * @param status looking for
     * @return map of orders withh timestamp as key
     * todo: reverse order
     */
    public Map<Instant, Order> getOldestOrderPerStatus(OrderStatus status){
        Map<Instant, Order> orderMap= new HashMap<>();
        List<Order> orderList= orderRepo.getOrder(status);

        for (Order o: orderList){
            orderMap.put(o.orderTimestamp(), o);
        }
        return orderMap;
    }
}
