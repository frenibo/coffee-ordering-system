// Singleton Pattern for Order Manager
import java.util.*;

class OrderManager {
    private static OrderManager instance;
    private List<Order> orders;
    
    private OrderManager() {
        orders = new ArrayList<>();
    }
    
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }
    
    public void registerOrder(Order order) {
        orders.add(order);
    }
    
    public List<Order> getOrders() {
        return orders;
    }
}