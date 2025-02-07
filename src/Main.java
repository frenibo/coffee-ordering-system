import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        OrderFacade facade = new OrderFacade();
        OrderInvoker invoker = new OrderInvoker();
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.registerObserver(new BaristaDisplay());
        orderManager.registerObserver(new KitchenDisplay());

        List<Order> orders;

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Coffee Shop!");
            System.out.println("1. Place Order");
            System.out.println("2. See All Orders");
            System.out.println("3. Cancel Last Order");
            System.out.println("x -> Exit");
            System.out.print("Select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Place order
                    System.out.print("Choose coffee type:\n(1) Espresso\n(2) Latte\n(3) Cappuccino\n");
                    String type = scanner.nextLine();
                    Coffee coffee = CoffeeFactory.createCoffee(type);
                    if (coffee == null) {
                        System.out.println("Invalid coffee type!");
                        break;
                    }

                    // Decide on Milk & Sugar
                    System.out.print("Add milk to your " + coffee.getDescription() + "? (y/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        coffee = new MilkDecorator(coffee);
                    }
                    System.out.print("Add sugar to your " + coffee.getDescription() + "? (y/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        coffee = new SugarDecorator(coffee);
                    }

                    Order order = new Order(coffee);
                    // Create a command to place order
                    Command placeOrder = new PlaceOrderCommand(facade, order);
                    invoker.takeCommand(placeOrder);
                    invoker.processCommands();
                    break;

                case "2":
                    orders = orderManager.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders have been made.");
                    } else {
                        orders.forEach(action -> {
                            System.out.println(action.toString());
                        });
                    }
                    break;

                case "3":
                    // For demonstration, cancel the last order if exists.
                    orders = orderManager.getOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No orders to cancel!");
                    } else {
                        Order lastOrder = orders.get(orders.size() - 1);
                        Command cancelOrder = new CancelOrderCommand(lastOrder);
                        invoker.takeCommand(cancelOrder);
                        invoker.processCommands();
                    }
                    break;
                    
                    

                case "x":
                    exit = true;
                    System.out.println("Thank you for visiting!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}