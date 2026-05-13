import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        OrderMapRepo mapRepo= new OrderMapRepo();
        ShopService shop= new ShopService(mapRepo);
        Product product1= new Product(1, "Icecream", 1.99);
        Product product2= new Product(2, "Whiskey", 19.99);
        List<Product> productList= new ArrayList<>();

        productList.add(product1);
        productList.add(product2);
        shop.customerOrder("MR", productList);
        shop.customerOrder("AD", productList);
        System.out.println(shop.showOrder(2));
        System.out.println(shop.showOrder(1));
    }
}
