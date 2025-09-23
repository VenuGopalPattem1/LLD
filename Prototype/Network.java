package Prototype;

public class Network {
public static void main(String[] args) {
    NetworkComponenets nc=new Router("Router1","111.111.1.1");
    Router r1=(Router) nc.clone();
    Router r2=(Router) nc.clone();
    r1.display();
    if(r1.equals(r2)){
        System.out.println("Same Object");
    }else{
        System.out.println("Different Object");
    }
    NetworkComponenets nc2=new Switch("Switch1",24);
    Switch s1=(Switch) nc2.clone(); 
    s1.display();
}
}
interface NetworkComponenets{
    public NetworkComponenets clone();
    public void display();
}

class Router implements NetworkComponenets{
    private String name;
    private String ipAdree;

    public Router(String name,String ipAdree){
        this.name = name;
        this.ipAdree = ipAdree;
    }

    public NetworkComponenets clone(){
        return new Router(name, ipAdree);
    }

    public void display(){
        System.out.println("Router Name: "+name+" IP Address: "+ipAdree);
    }
}

class Switch implements NetworkComponenets{
    private String name;
    private int ports;

    public Switch(String name,int ports){
        this.name = name;
        this.ports = ports;
    }

    public NetworkComponenets clone(){
        return new Switch(name, ports);
    }

    public void display(){
        System.out.println("Switch Name: "+name+" Number of Ports: "+ports);
    }
}