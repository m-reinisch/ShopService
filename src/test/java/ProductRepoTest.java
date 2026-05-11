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
    void storeProduct_shouldBeFalse_whenFailed() {
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(1, "Whiskey", 19.99);
        ProductRepo warehouse= new ProductRepo();
        Boolean expected= false;
        Boolean actual;

        warehouse.storeProduct(product1);
        actual= warehouse.storeProduct(product2);
        assertEquals(expected, actual);
    }

    @Test
    void removeProduct_shouldBeFase_whenFailed() {
        ProductRepo warehouse= new ProductRepo();
        Boolean expected= false;
        Boolean actual;

        actual= warehouse.removeProduct(Integer.valueOf(1));
        assertEquals(expected, actual);
    }

    @Test
    void removeProduct_shouldBeTrue_whenSuccessful() {
        Product product1= new Product(1, "Icecream", 1.99);
        ProductRepo warehouse= new ProductRepo();
        Boolean expected= true;
        Boolean actual;

        warehouse.storeProduct(product1);
        actual= warehouse.removeProduct(Integer.valueOf(1));
        assertEquals(expected, actual);
    }

    @Test
    void productInquiry_shouldBeIcecream_whenSuccessful() {
        Product product1= new Product(1, "Icecream", 1.99);
        ProductRepo warehouse= new ProductRepo();
        String expected= "1 Icecream 1.99";
        String actual;

        warehouse.storeProduct(product1);
        actual= warehouse.productInquiry(Integer.valueOf(1));
        assertEquals(expected, actual);
    }

    @Test
    void productInquiry_shouldBeNoProduct_whenFailed() {
        ProductRepo warehouse= new ProductRepo();
        String expected= "Product not available!";
        String actual;

        actual= warehouse.productInquiry(Integer.valueOf(1));
        assertEquals(expected, actual);
    }

    @Test
    void productInquiry_shouldBeIcecreamWhiskey_whenSuccessful() {
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        ProductRepo warehouse= new ProductRepo();
        String expected= "1 Icecream 1.99\n2 Whiskey 19.99\n";
        String actual;

        warehouse.storeProduct(product1);
        warehouse.storeProduct(product2);
        actual= warehouse.productInquiry();
        assertEquals(expected, actual);
    }

    @Test
    void findByProductName_shouldBe2_whenFoundWhiskey(){
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        ProductRepo warehouse= new ProductRepo();
        Integer expected= 2;
        Integer actual;

        warehouse.storeProduct(product1);
        warehouse.storeProduct(product2);
        actual= warehouse.findByProductMame("Whiskey");
        assertEquals(expected, actual);
    }

    @Test
    void findByProductName_shouldBe0_whenNotoundWhisky(){
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        ProductRepo warehouse= new ProductRepo();
        Integer expected= 0;
        Integer actual;

        warehouse.storeProduct(product1);
        warehouse.storeProduct(product2);
        actual= warehouse.findByProductMame("Whisky");
        assertEquals(expected, actual);
    }
}