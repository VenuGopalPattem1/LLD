package Observer;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus {
    public static void main(String[] args) {
        Order o=new Order("Pizza");
        Observer c=new Customer("Venu");
        Observer r=new Resturant("Domino's");
        Observer d=new Delivery("DHL");
        Observer cc=new CallCenter();
        o.add(c);
        o.add(r);
        o.add(d);
        o.add(cc);
        o.updateStatus("Order Placed");
        System.out.println("--------------------------------");
        o.delete(cc);o.delete(r);
        o.updateStatus("Out for Delivery");

    }
}
//subject
class Order{
    private String item;
    private String status;
    List<Observer> li=new ArrayList<>();

    public Order(String item){
        this.item=item;
        // this.status="Order Placed";
    }
    public String getItem() {
        return item;
    }
    public String getStatus() {
        return status;
    }
    public void updateStatus(String status){
        this.status=status;
        notifyAllObservers();
    }
    public void add(Observer o){
        li.add(o);
    }
    public void delete(Observer o){
        li.remove(o);
    }
    private void notifyAllObservers(){
        for(Observer o:li){
            o.update(this);
        }
    }
}

//observer
interface Observer{
    public void update(Order order);
}
//concrete observers
class Customer implements Observer{
    private String name;
    public Customer(String name){
        this.name=name;
    }
    public void update(Order order) {
        System.out.println("Hello "+name+", your order for "+order.getItem()+" is now confirmed");
    }

}
//concrete observers
class Resturant implements Observer{
    private String name;
    public Resturant(String name){
        this.name=name;
    }
    public void update(Order order) {
       System.out.println("Hello "+name+", order for "+order.getItem()+" is now "+order.getStatus());    
  }

}
//concrete observers
class Delivery implements Observer{
    private String name;
    public Delivery(String name){
        this.name=name;
    }
    public void update(Order order) {
        System.out.println("Hello "+name+", order for "+order.getItem()+" is now out for delivery");
  }
}
//concrete observers
class CallCenter implements Observer{

    public void update(Order order) {
        System.out.println("Call Center notified for order of "+order.getItem()+" with status "+order.getStatus());}
}

