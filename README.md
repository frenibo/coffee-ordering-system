# Coffee Shop Ordering System

GitHub Repository: https://github.com/frenibo/coffee-ordering-system

This Java console application simulates a simple coffee shop ordering system. It demonstrates the use of six design patterns:

- **Creational Patterns:**
  - **Factory Pattern**
  - **Singleton Pattern**

- **Structural Patterns:**
  - **Decorator Pattern**
  - **Facade Pattern**

- **Behavioral Patterns:**
  - **Observer Pattern**
  - **Command Pattern**

## Overview

The Coffee Shop program allows users to:
- Place a coffee order by selecting a type of coffee (Espresso, Latte, or Cappuccino).
- Customize their coffee with extra ingredients (e.g., milk and sugar).
- Process orders via a centralized order manager.
- Cancel the most recent order.
- See notifications of order events in different parts of the system (e.g., for the barista or kitchen).

Each design pattern is used as follows:

## Creational Patterns

### Factory Pattern
- **Where it's used:** `CoffeeFactory`
- **Purpose:** The `CoffeeFactory.createCoffee(String type)` method abstracts the creation of various coffee objects. Depending on the input (e.g., "Espresso", "Latte", or "Cappuccino"), the factory returns the appropriate `Coffee` subclass instance.
- **Benefits:**  
  - Simplifies object creation and centralizes instantiation logic.
  - Enables easier expansion when new coffee types are added.
- **Code:**
```java
class CoffeeFactory {
    public static Coffee createCoffee(String type) {
        if (type.equalsIgnoreCase("espresso") || type.equalsIgnoreCase("1")) {
            return new Espresso();
        } else if (type.equalsIgnoreCase("latte") || type.equalsIgnoreCase("2")) {
            return new Latte();
        } else if (type.equalsIgnoreCase("cappuccino") || type.equalsIgnoreCase("3")) {
            return new Cappuccino();
        } else {
            return null;
        }
    }
}
```

### Singleton Pattern
- **Where it's used:** `OrderManager`
- **Purpose:** The `OrderManager` class is designed as a singleton so that only one instance manages all orders and notifies observers.
- **Benefits:**  
  - Ensures a consistent state across the application.
  - Provides a single point of access to order management and observer registration.
- **Code:**
```java
class OrderManager {
    private static OrderManager instance;

    ...
    
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }
    ...
}
```

## Structural Patterns

### Decorator Pattern
- **Where it's used:** `CoffeeDecorator`, `MilkDecorator`, and `SugarDecorator`
- **Purpose:** The decorator pattern is used to add extra ingredients to a coffee order dynamically. Decorators wrap the base `Coffee` object and extend its functionality by modifying the description and cost.
- **Benefits:**  
  - Adds flexibility in how coffee objects are customized.
  - Allows for a combination of features without creating numerous subclasses.
- **Code:**
```java
abstract class CoffeeDecorator extends Coffee {
    protected Coffee coffee;
    @Override
    public abstract String getDescription();
    @Override
    public abstract double cost();
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + " & Milk";
    }
    
    @Override
    public double cost() {
        return coffee.cost() + 0.50;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + " & Sugar";
    }
    
    @Override
    public double cost() {
        return coffee.cost() + 0.25;
    }
}
```

### Facade Pattern
- **Where it's used:** `OrderFacade`
- **Purpose:** The `OrderFacade` class provides a simplified interface (`processOrder`) to process orders. It hides the complexities of order registration, observer notification, and any additional business logic.
- **Benefits:**  
  - Simplifies interactions for the client (the main program).
  - Encapsulates complex processes behind a simple API.
- **Code:**
```java
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
```

## Behavioral Patterns

### Observer Pattern
- **Where it's used:**  
  - The `OrderManager` maintains a list of `OrderObserver` instances.
  - Observers like `BaristaDisplay` and `KitchenDisplay` are notified whenever a new order is registered.
- **Purpose:**  
  - Automatically updates different parts of the system when order events occur.
  - Decouples the order management logic from the notification logic.
- **Benefits:**  
  - Enhances modularity and scalability.
  - Enables multiple parts of the system to respond to changes independently.
- **Code:**
```java
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
```

### Command Pattern
- **Where it's used:**  
  - `PlaceOrderCommand` and `CancelOrderCommand` encapsulate the actions of placing and canceling orders.
  - `OrderInvoker` queues and executes these commands.
- **Purpose:**  
  - Encapsulates user actions as objects, making it possible to log, queue, or even undo commands.
- **Benefits:**  
  - Provides a flexible way to execute operations.
  - Separates the object that invokes the command from the object that performs the command.
- **Code:**
```java
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
```