// Facade Pattern for Order Processing
class OrderFacade {
    private OrderManager orderManager;
    
    public OrderFacade() {
        orderManager = OrderManager.getInstance();
    }
    
    public void processOrder(Order order) {
        // Process the order: register it and notify observers.
        orderManager.registerOrder(order);
        System.out.println("Processing order: " + order);
        // Here a more complex logic could be implemented (payment, preparation, etc.)
    }
}