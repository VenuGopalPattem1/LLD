package Builder;

public class BuilderDirectorChaining {
    public static void main(String[] args) {
        Director d=new Director(new DellComputer());
        Computer hp=d.getComputer();
        System.out.println(hp);
    }
}
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
    public Builder  setCpu();
    public Builder setRam();
    public Builder setStorage();
    public Builder setGraphicCard();
    public Computer getComputer();
}

//concrete builder class
class HpComputer implements Builder{
    private Computer computer=new Computer();

    public Builder setCpu() {
        computer.setCpu("Intel i7");
        return this;
    }

    public Builder setRam() {
        computer.setRam("16GB");
        return this;

    }

    public Builder setStorage() {
        computer.setStorage("1TB SSD");
        return this;

    }

    public Builder setGraphicCard() {
        computer.setGraphicCard(true);
        return this;

    }
    public Computer getComputer(){
        return computer;
    }
}

// concrete builder class
class DellComputer implements Builder{
    private Computer computer=new Computer();

    public Builder setCpu() {
        computer.setCpu("AMD Ryzen 7");
        return this;
    }

    public Builder setRam() {
        computer.setRam("32GB");
        return this;
    }

    public Builder setStorage() {
        computer.setStorage("2TB SSD");
        return this;
    }

    public Builder setGraphicCard() {
        computer.setGraphicCard(false);
        return this;
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
        return build.setCpu().setRam().setStorage().setGraphicCard().getComputer();
    }
}