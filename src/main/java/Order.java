import lombok.With;
import java.util.List;

@With
public record Order(Integer id,
                    String name,
                    String costumer,
                    List<Product> products,
                    Double totalPrice,
                    OrderStatus status) {
}
