// Singleton Pattern for Order Manager
import java.util.*;

class OrderManager {
    private static OrderManager instance;
    private List<OrderObserver> observers;
    private List<Order> orders;
    
    private OrderManager() {
        observers = new ArrayList<>();
        orders = new ArrayList<>();
    }
    
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }
    
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
    
    public void registerOrder(Order order) {
        orders.add(order);
        notifyObservers(order);
    }
    
    public List<Order> getOrders() {
        return orders;
    }
}