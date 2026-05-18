import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void customerOrder_shouldThrowException_whenOneProductFailed() {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whisky", 19.99);
        List<Product> productList= new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
        assertThrows(ProductOutOfStockException.class, () -> shop.customerOrder("MR", productList));
    }

    @Test
    void customerOrder() throws ProductOutOfStockException {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        List<Product> productList= new ArrayList<>();
        Integer orderId;
        String expected = "3 Custom Order MR [Product[id=1, name=Icecream, price=1.99], Product[id=2, name=Whiskey, price=19.99]] 21.98 PROCESSING";
        String actual;

        productList.add(product1);
        productList.add(product2);
        orderId= shop.customerOrder("MR", productList);
        actual= shop.showOrder(orderId);
        assertEquals(expected, actual);
    }

    @Test
    void showOrder_shouldBeNotFound_whenWrongCustomer() {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Integer orderId= 1;
        String expected = "Order not found!";
        String actual;

        actual= shop.showOrder(orderId);
        assertEquals(expected, actual);
    }

    @Test
    void showOrder_shouldBeOrder_whenCustomer() throws ProductOutOfStockException {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        Integer orderId;
        String expected = "1 Custom Order MR [Product[id=1, name=Icecream, price=1.99]] 1.99 PROCESSING";
        String actual;

        productList.add(product);
        orderId= shop.customerOrder("MR", productList);
        actual= shop.showOrder(orderId);
        assertEquals(expected, actual);
    }

    @Test
    void listOrders_shouldBeNULL_whenNotFound(){
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);

        assertThat(shop.listOrders(OrderStatus.PROCESSING))
                .isEqualTo(null);
    }

    @Test
    void listOrders_shouldBeStream_whenFound() throws ProductOutOfStockException {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        Integer orderId;

        productList.add(product);
        orderId= shop.customerOrder("MR", productList);
        assertThat(shop.listOrders(OrderStatus.PROCESSING)
                .toList().getFirst().id())
                .isEqualTo(orderId);
    }
}