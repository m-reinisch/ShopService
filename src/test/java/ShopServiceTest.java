import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void customerOrder_shouldBeNotAvailable_whenOneProductFailed() {
        OrderMapRepo mapRepo= new OrderMapRepo();
        ShopService shop= new ShopService(mapRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whisky", 19.99);
        List<Product> productList= new ArrayList<>();
        String expected= "Product not available!";
        String actual;

        productList.add(product1);
        productList.add(product2);
        Order order= new Order(1, "Mixed Order", "MR", productList, 21.98);
        shop.customerOrder("MR", productList);
//        assertEquals(expected, actual);
    }

    @Test
    void customerOrder() {
        OrderMapRepo mapRepo= new OrderMapRepo();
        ShopService shop= new ShopService(mapRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        List<Product> productList= new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
        Order order= new Order(1, "Mixed Order", "MR", productList, 21.98);
        shop.customerOrder("MR", productList);
        ;
    }
}