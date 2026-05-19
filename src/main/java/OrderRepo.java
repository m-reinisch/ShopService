import java.util.List;

public interface OrderRepo {
    public Boolean addOrder(Order order);
    public Boolean removeOrder(Integer orderId);
    public String orderInquiry(Integer id);
    public String orderInquiry();
    public List<Order> getOrder(OrderStatus status);
    public Order getOrder(Integer id);
}
