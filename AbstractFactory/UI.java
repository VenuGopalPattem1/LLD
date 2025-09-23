package AbstractFactory;

public class UI {
    public static void main(String[] args) {

        GUIFactory gui=new WindowsFactory();
        Button b=gui.createButton();
        CheckBox c=gui.createCheckBox();
        b.click();
        c.select();

        // GUIFactory gui=new MacFactory();
        // Button b=gui.createButton();
        // CheckBox c=gui.createCheckBox();
        // b.click();
        // c.select();
    }
}
//Abstract Product Interfaces
interface Button{
    void click();
}
//Abstract Product Interfaces
interface CheckBox{
    void select();
}
//Concrete Products
//these are Windows family of products
class WindosButton implements Button{
    @Override
    public void click() {
        System.out.println("This is Windows Button");
    }
}
//Concrete Products
class WindosCheckBox implements CheckBox{
    @Override
    public void select() {
        System.out.println("This is Windows CheckBox");
    }
}
//Concrete Products
//these are Mac family of products
class MacButton implements Button{
    @Override
    public void click() {
        System.out.println("This is Mac Button");
    }
}
//Concrete Products
class MacCheckBox implements CheckBox{
    @Override
    public void select() {
        System.out.println("This is Mac CheckBox");
    }
}

//this is Abstract Factory interface
interface GUIFactory{
    Button createButton();
    CheckBox createCheckBox();
}


//these are Factory classes for each family of products
// Concrete Factories
//Windows Factory
class WindowsFactory implements GUIFactory{

    @Override
    public Button createButton() {
        return new WindosButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindosCheckBox();
    }
    
}
// Concrete Factories
//Mac Factory
class MacFactory implements GUIFactory{

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
    
}
