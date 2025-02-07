// Command Pattern to Encapsulate Order Actions
interface Command {
    void execute();
}

class PlaceOrderCommand implements Command {
    private OrderFacade facade;
    private Order order;
    
    public PlaceOrderCommand(OrderFacade facade, Order order) {
        this.facade = facade;
        this.order = order;
    }
    
    @Override
    public void execute() {
        facade.processOrder(order);
    }
}

class CancelOrderCommand implements Command {
    private OrderManager orderManager;
    private Order order;
    
    public CancelOrderCommand(Order order) {
        this.order = order;
        this.orderManager = OrderManager.getInstance();
    }
    
    @Override
    public void execute() {
        // For simplicity, we just remove the order if it exists.
        if (orderManager.getOrders().remove(order)) {
            System.out.println("Order canceled: " + order);
        } else {
            System.out.println("Order not found: " + order);
        }
    }
}