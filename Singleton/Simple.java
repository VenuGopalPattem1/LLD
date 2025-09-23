package Singleton;

public class Simple {
    public static void main(String[] args) {
        Singleton a=Singleton.getInstance();
        Singleton b=Singleton.getInstance();
        if(a.equals(b)){
            System.out.println("Same Instance");
        }
        else{
            System.out.println("Different Instance");
        }
    }
}
class Singleton{
    private static Singleton instance=null;
    private Singleton(){};
    public static  Singleton getInstance(){
        if(instance==null){
            instance=new Singleton();
        }
        return instance;
    }
}
