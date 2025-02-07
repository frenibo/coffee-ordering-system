import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Coffee Shop!");
            System.out.println("1. Place Order");
            System.out.println("X -> Exit");
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

                    System.out.print("You ordered " + coffee.getDescription() + ".");
                    break;

                case "X":
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