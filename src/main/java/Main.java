import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList);
        OrderListRepo orderListRepo= new OrderListRepo();

        orderListRepo.addOrder(order);
        productList.clear();
        product= new Product(2, "Whiskey", 19.99);
        productList.add(product);
        order= new Order(2, "Order Spirit", "MR", productList);
        orderListRepo.addOrder(order);
        System.out.println(orderListRepo.orderInquiry());
    }
}
