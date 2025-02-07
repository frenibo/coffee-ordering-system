abstract class Coffee {
    protected String description = "Unknown Coffee";
    
    public String getDescription() {
        return description;
    }
    
    public abstract double cost();
}

class Espresso extends Coffee {
    public Espresso() {
        description = "Espresso";
    }
    
    @Override
    public double cost() {
        return 2.00;
    }
}

class Latte extends Coffee {
    public Latte() {
        description = "Latte";
    }
    
    @Override
    public double cost() {
        return 3.00;
    }
}

class Cappuccino extends Coffee {
    public Cappuccino() {
        description = "Cappuccino";
    }
    
    @Override
    public double cost() {
        return 3.50;
    }
}

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