package Oops;
class Polymorphism{
    void see(){
        System.out.println("this in main class");
    }
}

class Venu extends Polymorphism{
    void see(){
        System.out.println("this is venu method");
    }
    public static void main(String[] args) {
        Polymorphism ph=new Venu();
        ph.see();
    }
}