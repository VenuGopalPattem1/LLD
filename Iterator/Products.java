package Iterator;

import java.util.ArrayList;
import java.util.List;

public class Products {
    public static void main(String[] args) {
        Inventory in=new Inventory();
        Item a=new Item("chair", 201, 3);
        Item b=new Item("laptop", 2001, 1);
        Item c=new Item("bag", 2101, 4);
        Item d=new Item("monitor", 3301, 2);
        in.addItem(a);
        in.addItem(b);
        in.addItem(c);
        in.addItem(d);
        Iterator it=in.getIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
//item
class Item{
    private String name;
    private int price;
    private int quantity;

    public Item(String name,int price,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    public String toString(){
        return "[item : name - "+name+", price - "+price+", quantity - "+quantity+" ]";
    }
}
//iterator
interface Iterator{
    public Item first();
    public Item next();
    public boolean hasNext();
}
//concrete iterator
class ItemIterator implements Iterator{
    private List<Item> items;
    private int curr;
    public ItemIterator(List<Item> items){
        this.items=items;
        this.curr=0;
    }
    @Override
    public Item first() {
        if(items.size()>0){
            curr=0;
            return items.get(curr);
        }
        return null;
    }
    @Override
    public Item next() {
        if(curr<items.size()){
            return items.get(curr++);
        }
        return null;
    }
    @Override
    public boolean hasNext() {
        return curr<items.size();
    }
}
//inventory(aggregate)
class Inventory{
    List<Item> li=new ArrayList<>();

    public void addItem(Item i){
        li.add(i);
    }
    public Iterator getIterator(){
        return new ItemIterator(li);
    }
}