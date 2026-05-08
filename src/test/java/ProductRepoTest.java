import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void storeProduct_shouldBeAvailable_whenSuccessful() {
        Product product= new Product(1, "Icecream", 1.99);
        ProductRepo warehouse= new ProductRepo();
        Integer expected= 1;
        Integer actual;

        warehouse.storeProduct(product);
        actual= warehouse.getWarehouse().get(product.id()).id();
        assertEquals(expected, actual);
    }

    @Test
    void storeProduct_shouldBeFase_whenFailed() {
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(1, "Whiskey", 19.99);
        ProductRepo warehouse= new ProductRepo();
        Boolean expected= false;
        Boolean actual;

        warehouse.storeProduct(product1);
        actual= warehouse.storeProduct(product2);
        assertEquals(expected, actual);
    }
}