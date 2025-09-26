package Templete;

public class Delivery {
//     Defining the skeleton of an algorithm, allowing certain steps to be implemented by subclasses while maintaining
//  the overall structure. This is useful when you have a fixed sequence of operations but want to allow variations in specific steps.
    public static void main(String[] args) {
        DeliverSystem ds=new LocalDelivery();
        ds.processOrder();
        ds=new InternationalDelivery();
        System.out.println("-------------------");
        ds.processOrder();
    }
}

abstract class DeliverSystem{
    public void processOrder(){
        verifyOrder();
        packageOrder();
        shipOrder();
        delivery();
    }
    public abstract void verifyOrder();
    public abstract void packageOrder();
    public abstract void shipOrder();   
    public abstract void delivery();
}

class LocalDelivery extends DeliverSystem{
    public void verifyOrder(){
        System.out.println("verifying order for local delivery");
    }
    public void packageOrder(){
        System.out.println("packaging order for local delivery");
    }
    public void shipOrder(){
        System.out.println("shipping order for local delivery");
    }
    public void delivery(){
        System.out.println("delivering order for local delivery");
    }
}

class InternationalDelivery extends DeliverSystem{
    public void verifyOrder(){
        System.out.println("verifying order for international delivery");
    }
    public void packageOrder(){
        System.out.println("packaging order for international delivery");
    }
    public void shipOrder(){
        System.out.println("shipping order for international delivery");
    }
    public void delivery(){
        System.out.println("delivering order for international delivery");
    }
}
