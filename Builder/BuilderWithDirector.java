package Builder;
//client

// GoF Builder → Use when you need to create multiple variants of a complex object (different representations) with the
//  same sequence of steps (via a Director). Helps avoid large constructors when many similar but different products exist.

// Example: GamingComputer, OfficeComputer, ServerComputer.
public class BuilderWithDirector {
    public static void main(String[] args) {
        Director d=new Director(new HpComputer());
        Computer hp=d.getComputer();
        System.out.println(hp);

        Director dell=new Director(new DellComputer());
        Computer del=dell.getComputer();
        System.out.println(del);
    }
}
//Product class
class Computer{
    private String cpu;
    private String ram;
    private String storage;
    private boolean graphicCard;

    public String toString(){
        return "Computer [cpu=" + cpu + ", ram=" + ram + ", storage=" + storage + ", graphicCard=" + graphicCard + "]";
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }   
    public void setRam(String ram) {
        this.ram = ram;
    }
    public void setStorage(String storage) {
        this.storage = storage;
    }
    public void setGraphicCard(boolean graphicCard) {
        this.graphicCard = graphicCard;
    }
}

//builder Interface
interface  Builder{
    public void  setCpu();
    public void setRam();
    public void setStorage();
    public void setGraphicCard();
    public Computer getComputer();
}

//concrete builder class
class HpComputer implements Builder{
    private Computer computer=new Computer();

    public void setCpu() {
        computer.setCpu("Intel i7");
    }

    public void setRam() {
        computer.setRam("16GB");
    }

    public void setStorage() {
        computer.setStorage("1TB SSD");
    }

    public void setGraphicCard() {
        computer.setGraphicCard(true);
    }
    public Computer getComputer(){
        return computer;
    }
}

//concrete builder class
class DellComputer implements Builder{
    private Computer computer=new Computer();

    public void setCpu() {
        computer.setCpu("AMD Ryzen 7");
    }

    public void setRam() {
        computer.setRam("32GB");
    }

    public void setStorage() {
        computer.setStorage("2TB SSD");
    }

    public void setGraphicCard() {
        computer.setGraphicCard(false);
    }
    public Computer getComputer(){
        return computer;
    }
}

//Director class
class Director{
    private Builder build;
    Director(Builder builder){
        this.build=builder;
    }

    Computer getComputer(){
        build.setCpu();
        build.setRam();
        build.setStorage();
        build.setGraphicCard();
        return build.getComputer();
    }
}