import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/** A service through which we can place new orders.
 *
 * @author Michael Reinisch
 */
public class ShopService {
    private final ProductRepo warehouse= new ProductRepo();
    private final OrderRepo orderRepo;
    private static Integer orderNumber= 0;

    public ShopService(OrderRepo orderRepo) {
        this.orderRepo= orderRepo;
        genrateInventory();
    }

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

    /** Internal method for product generation
     *
     */
    private void genrateInventory(){
        int i= 1;

        for ( ; i < 11; i++){
            Product product= new Product(i, "Icecream", 1.99);
            warehouse.storeProduct(product);
        }
        for ( ; i < 18; i++){
            Product product= new Product(i, "Whiskey", 19.99);
            warehouse.storeProduct(product);
        }
    }
}
