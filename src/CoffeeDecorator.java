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