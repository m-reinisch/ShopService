public interface OrderRepo {
    public Boolean addOrder(Order order);
    public Boolean removeOrder(Integer orderId);
    public Order orderInquiry(Integer id);
    public Order orderInquiry();
}
