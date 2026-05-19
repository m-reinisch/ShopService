import lombok.With;
import java.time.Instant;
import java.util.List;

public record Order(Integer id,
                    String name,
                    String costumer,
                    List<Product> products,
                    Double totalPrice,
                    @With
                    OrderStatus status,
                    Instant orderTimestamp) {
}
