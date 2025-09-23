package Builder;

public class ModerFluientBuilder {

    // Fluent Builder (Modern) → Use when you’re creating a single object with many optional parameters,
    //  and you want to avoid a telescoping constructor. Helps keep code readable and immutable.

   // Example: A User object with name (required) + email, phone, address (optional).
    public static void main(String[] args) {
        Computer comp1=new Computer.Builder().setCpu("Intel i7")
                        .setRam("16GB")
                        .setStorage("1TB SSD")
                        .setGraphicCard(true)
                        .build();
        System.out.println(comp1);

        Computer comp2=new Computer.Builder().setCpu("AMD Ryzen 7")
                        .setRam("32GB")
                        .setStorage("2TB SSD")
                        .setGraphicCard(false)
                        .build();
        System.out.println(comp2);
    }
}

// product class
class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private boolean graphicsCard;

   // private constructor to enforce object creation through Builder
    private Computer(Builder build){
        this.cpu=build.cpu;
        this.ram=build.ram;
        this.storage=build.storage;
        this.graphicsCard=build.graphicsCard;
    }

    public String toString(){
        return "Computer [cpu=" + cpu + ", ram=" + ram + ", storage=" + storage + ", graphicsCard=" + graphicsCard + "]";
    }
    // Builder Class
    // static nested class
    public static class Builder{
        private String cpu;
        private String ram;
        private String storage;
        private boolean graphicsCard;

        public Builder setCpu(String cpu){
            this.cpu=cpu;
            return this;
        }

        public Builder setRam(String ram){
            this.ram=ram;
            return this;
        }
        public Builder setStorage(String storage){
            this.storage=storage;
            return this;
        }
        public Builder setGraphicCard(boolean graphicCard){
            this.graphicsCard=graphicCard;
            return this;
        }

        // method to return the final object to product class constructor
        public Computer build(){
            return new Computer(this); //this refers to the Builder class instance
        }
    }
}