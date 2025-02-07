// Observer Pattern to Notify When Order is Placed
interface OrderObserver {
    void update(Order order);
}

class BaristaDisplay implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Barista Display: New order received -> " + order);
    }
}

class KitchenDisplay implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Kitchen Display: Prepare order -> " + order);
    }
}