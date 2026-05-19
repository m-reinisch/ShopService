import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
        String timestamp= LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"));
        String expected = "4 Custom Order MR [Product[id=1, name=Icecream, price=1.99], Product[id=2, name=Whiskey, price=19.99]] 21.98 PROCESSING " + timestamp;
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
        String timestamp= LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"));
        String expected = "2 Custom Order MR [Product[id=1, name=Icecream, price=1.99]] 1.99 PROCESSING " + timestamp;
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

    @Test
    void updateOrder_shouldBeFalse_whenNotFound() {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);

        assertThat(shop.updateOrder(1, OrderStatus.IN_DELIVERY))
                .isFalse();
    }

    @Test
    void updateOrder_shouldBeTrue_whenUpdated() throws ProductOutOfStockException {
        OrderRepo orderRepo= new OrderMapRepo();
        ShopService shop= new ShopService(orderRepo);
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        Integer orderId;

        productList.add(product);
        orderId= shop.customerOrder("MR", productList);
        assertThat(shop.updateOrder(orderId, OrderStatus.IN_DELIVERY))
                .isTrue();
    }
}