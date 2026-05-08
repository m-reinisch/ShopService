import java.util.HashMap;
import java.util.Map;

/** Order Filing Folder
 *
 * @author Michael Reinisch
 */
public class OrderListRepo implements OrderRepo {
    private final Map<Integer, Order> orderMap= new HashMap<>();

    public Boolean addOrder(Order order){
        return false;
    }

    public Boolean removeOrder(Integer orderId){
        return false;
    }

    public Order orderInquiry(Integer id){
        return orderMap.get(0);
    }

    /**
     *
     * @return
     */
    public Order orderInquiry(){
        return orderMap.get(0);
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }
}
