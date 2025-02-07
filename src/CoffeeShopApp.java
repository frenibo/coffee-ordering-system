import java.util.*;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                exit = true;
            }
        }
        scanner.close();
    }
}