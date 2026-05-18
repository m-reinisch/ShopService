import java.util.ArrayList;
import java.util.List;

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
     */
    public Integer customerOrder(String customerName, List<Product> products){
        List<Product> orderList= new ArrayList<>();
        Double totalPrice= 0.0;

        for (int n= 0; n < products.size(); n++){
            if (warehouse.findByProductMame(products.get(n).name()) == 0){
                System.out.println("Product not available!");
            } else {
                orderList.add(products.get(n));
                totalPrice= totalPrice + products.get(n).price();
            }
        }
        Order order= new Order(++orderNumber, "Custom Order", customerName, orderList, totalPrice, OrderStatus.PROCESSING);
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
