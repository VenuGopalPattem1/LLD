package Singleton;

public class DoubleCheckedLock {
    public static void main(String[] args) {
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();
        a.fun();
        if(a==b)
            System.out.println("a and b are same instance");    
    }
}

class Singleton {
    private static volatile Singleton instance = null; //volatioe  ->> risk of partially initialized object due to reordering.

    private Singleton() {
    };

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void fun() {
        System.out.println("Hello");
    }
}
