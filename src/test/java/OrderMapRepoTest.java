import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void addOrder_shouldBeTrue_whenSuccessful() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99, OrderStatus.PROCESSING);
        Boolean expected= true;
        Boolean actual;

        actual= orderMapRepo.addOrder(order);
        assertEquals(expected, actual);
    }

    @Test
    void addOrder_shouldBeFalse_whenFailed() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order1= new Order(1, "Order Icecream", "MR", productList, 1.99, OrderStatus.PROCESSING);
        Order order2= new Order(1, "Order Icecream", "MR", productList, 1.99, OrderStatus.PROCESSING);
        Boolean expected= false;
        Boolean actual;

        orderMapRepo.addOrder(order1);
        actual= orderMapRepo.addOrder(order2);
        assertEquals(expected, actual);
    }

    @Test
    void removeOrder_shouldBeFalse_whenFailed() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Boolean expected= false;
        Boolean actual;

        actual= orderMapRepo.removeOrder(1);
        assertEquals(expected, actual);
    }

    @Test
    void removeOrder_shouldBeTrue_whenSuccessful() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99, OrderStatus.PROCESSING);
        Boolean expected= true;
        Boolean actual;

        orderMapRepo.addOrder(order);
        actual= orderMapRepo.removeOrder(1);
        assertEquals(expected, actual);
    }

    @Test
    void orderInquiry_shouldBeNoOrder_whenFailed() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        String expected= "Order not available!";
        String actual;

        actual= orderMapRepo.orderInquiry(1);
        assertEquals(expected, actual);
    }

    @Test
    void orderInquiry_shouldBeOrder_whenSuccessful() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99, OrderStatus.PROCESSING);
        String expected= "1 Order Icecream MR [Product[id=1, name=Icecream, price=1.99]] 1.99 PROCESSING";
        String actual;

        orderMapRepo.addOrder(order);
        actual= orderMapRepo.orderInquiry(1);
        assertEquals(expected, actual);
    }

    @Test
    void testOrderInquiry() {
        OrderMapRepo orderMapRepo= new OrderMapRepo();
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        List<Product> productList1= new ArrayList<>();
        List<Product> productList2= new ArrayList<>();
        productList1.add(product1);
        Order order1= new Order(1, "Order Icecream", "MR", productList1, 1.99, OrderStatus.PROCESSING);
        productList2.add(product2);
        Order order2= new Order(2, "Order Spirit", "MR", productList2, 19.99, OrderStatus.PROCESSING);
        String expected= "1 Order Icecream MR [Product[id=1, name=Icecream, price=1.99]] 1.99 PROCESSING\n2 Order Spirit MR [Product[id=2, name=Whiskey, price=19.99]] 19.99 PROCESSING\n";
        String actual;

        orderMapRepo.addOrder(order1);
        orderMapRepo.addOrder(order2);
        actual= orderMapRepo.orderInquiry();
        assertEquals(expected, actual);
    }
}