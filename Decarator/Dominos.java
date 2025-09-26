package Decarator;


public class Dominos {
    public static void main(String[] args) {
        FoodItem pizza=new Pizza();
        FoodItem burger=new Burger();
        pizza=new  ExtraCheeseDecarator(pizza, 20);
        pizza=new ExtraSauseDecarator(pizza,23);
        pizza=new ExtraTopigsDecarator(pizza,52);

        burger=new  ExtraCheeseDecarator(burger,45);
        burger=new ExtraSauseDecarator(burger,22);
        burger=new ExtraTopigsDecarator(burger,19);

        System.out.println(pizza.name()+" price is "+pizza.price());
        System.out.println(burger.name()+" price is "+burger.price());

    }
}

interface FoodItem{
    public String name();
    public int price();
}

class Burger implements FoodItem{
    @Override
    public String name() {
        return "Burger";
    }

    @Override
    public int price() {
        return 50;
    }   
}

class Pizza implements FoodItem{
    @Override
    public String name() {
        return "Pizza";
    }

    @Override
    public int price() {
        return 100;
    }   
}  

//decorator class
abstract class Decarator implements FoodItem{
    protected FoodItem foodItem;
    public Decarator(FoodItem f){
        foodItem=f;
    }
}

class ExtraSauseDecarator extends Decarator{
    private int price;
    public ExtraSauseDecarator(FoodItem f,int price){
        super(f);
        this.price=price;
    }
    @Override
    public String name() {
        return foodItem.name()+" with Extra Sause";
    }

    @Override
    public int price() {
       return foodItem.price()+price;
    }
    
}

class ExtraTopigsDecarator extends Decarator{
    private int price;
    public ExtraTopigsDecarator(FoodItem f,int price){
        super(f);
        this.price=price;
    }
    @Override
    public String name() {
        return foodItem.name()+" with Extra Topings";
    }

    @Override
    public int price() {
       return foodItem.price()+price;
    }
    
}

class ExtraCheeseDecarator extends Decarator{
    private int price;
    public ExtraCheeseDecarator(FoodItem f,int price){
        super(f);
        this.price=price;
    }
    @Override
    public String name() {
        return foodItem.name()+" with Extra Cheese";
    }

    @Override
    public int price() {
       return foodItem.price()+price;
    }
    
}