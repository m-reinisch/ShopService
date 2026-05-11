public interface OrderRepo {
    public Boolean addOrder(Order order);
    public Boolean removeOrder(Integer orderId);
    public String orderInquiry(Integer id);
    public String orderInquiry();
}
