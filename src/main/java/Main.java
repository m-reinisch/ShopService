import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        OrderMapRepo mapRepo= new OrderMapRepo();
        ShopService shop= new ShopService(mapRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whisky", 19.99);
        List<Product> productList= new ArrayList<>();
        String expected= "Product not available!";
        String actual;

        productList.add(product1);
        productList.add(product2);
        Order order= new Order(1, "Mixed Order", "MR", productList);
        shop.customerOrder("MR", productList);
    }
}
