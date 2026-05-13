import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void addOrder_shouldBeTrue_whenSuccessful() {
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99);
        OrderListRepo orderListRepo= new OrderListRepo();
        Boolean expected= true;
        Boolean actual;

        actual= orderListRepo.addOrder(order);
        assertEquals(expected, actual);
    }

    @Test
    void addOrder_shouldBeFalse_whenFailed() {
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order1= new Order(1, "Order Icecream", "MR", productList, 1.99);
        Order order2= new Order(1, "Order Icecream", "MR", productList, 1.99);
        OrderListRepo orderListRepo= new OrderListRepo();
        Boolean expected= false;
        Boolean actual;

        orderListRepo.addOrder(order1);
        actual= orderListRepo.addOrder(order2);
        assertEquals(expected, actual);
    }

    @Test
    void removeOrder_shouldBeFalse_whenFailed() {
        OrderListRepo orderListRepo= new OrderListRepo();
        Boolean expected= false;
        Boolean actual;

        actual= orderListRepo.removeOrder(1);
        assertEquals(expected, actual);
    }

    @Test
    void removeOrder_shouldBeTrue_whenSuccessful() {
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99);
        OrderListRepo orderListRepo= new OrderListRepo();
        Boolean expected= true;
        Boolean actual;

        orderListRepo.addOrder(order);
        actual= orderListRepo.removeOrder(1);
        assertEquals(expected, actual);
    }

    @Test
    void orderInquiry_shouldBeNoOrder_whenFailed() {
        OrderListRepo orderListRepo= new OrderListRepo();
        String expected= "Order not available!";
        String actual;

        actual= orderListRepo.orderInquiry(1);
        assertEquals(expected, actual);
    }

    @Test
    void orderInquiry_shouldBeOrder_whenSuccessful() {
        Product product= new Product(1, "Icecream", 1.99);
        List<Product> productList= new ArrayList<>();
        productList.add(product);
        Order order= new Order(1, "Order Icecream", "MR", productList, 1.99);
        OrderListRepo orderListRepo= new OrderListRepo();
        String expected= "1 Order Icecream MR [Product[id=1, name=Icecream, price=1.99]] 1.99";
        String actual;

        orderListRepo.addOrder(order);
        actual= orderListRepo.orderInquiry(1);
        assertEquals(expected, actual);
    }

    @Test
    void testOrderInquiry() {
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        List<Product> productList1= new ArrayList<>();
        List<Product> productList2= new ArrayList<>();
        productList1.add(product1);
        Order order1= new Order(1, "Order Icecream", "MR", productList1, 1.99);
        productList2.add(product2);
        Order order2= new Order(2, "Order Spirit", "MR", productList2, 19.99);
        OrderListRepo orderListRepo= new OrderListRepo();
        String expected= "1 Order Icecream MR [Product[id=1, name=Icecream, price=1.99]] 1.99\n2 Order Spirit MR [Product[id=2, name=Whiskey, price=19.99]] 19.99\n";
        String actual;

        orderListRepo.addOrder(order1);
        orderListRepo.addOrder(order2);
        actual= orderListRepo.orderInquiry();
        assertEquals(expected, actual);
    }
}