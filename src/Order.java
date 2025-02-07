// Order class to hold details of an order
class Order {
    private static int count = 0;
    private int orderId;
    private Coffee coffee;
    
    public Order(Coffee coffee) {
        this.coffee = coffee;
        this.orderId = ++count;
    }
    
    public Coffee getCoffee() {
        return coffee;
    }
    
    @Override
    public String toString() {
        return "Order#" + orderId + " [" + coffee.getDescription() + " | $" + coffee.cost() + "]";
    }
}