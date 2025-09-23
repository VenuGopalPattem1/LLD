package Calculator;
//abstract factory
public class Calculator {
    public static void main(String[] args) {
        CalFactory c=new Financial();
        Display d=c.createDisplay();
        KeyPad k=c.creatKeyPad();
        d.show();
        k.keys();
    }
}

interface KeyPad{
    public void keys();
}

interface Display{
    public void show();
}

class ScientificCalDisplay implements Display{
    public void show(){
        System.out.println(" this is Scientific calculator Display  ");
    }
}

class FinancialCalDisplay implements Display{
    public void show(){
        System.out.println(" this is Financial calculator Display ");
    }
}

class NormalCalDisplay implements Display{
    public void show(){
        System.out.println(" this is Normal calculator Display ");
    }
}

class ScientificCalKeyPad implements KeyPad{
    public void keys(){
        System.out.println("this is ScientificCalKeyPad key pad");
    }
}

class FinancialCalKeyPad implements KeyPad{
    public void keys(){
        System.out.println("this is FinancialCalKeyPad key pad");
    }
}

class NormalCalKeyPad implements KeyPad{
    public void keys(){
        System.out.println("this is NormalCalKeyPad key pad");
    }
}

interface CalFactory{
    KeyPad creatKeyPad();
    Display createDisplay();
}

class Scientific implements CalFactory{

    @Override
    public KeyPad creatKeyPad() {
        return new ScientificCalKeyPad();
    }

    @Override
    public Display createDisplay() {
        return new ScientificCalDisplay();
    }
    
}

class Financial implements CalFactory{

    @Override
    public KeyPad creatKeyPad() {
        return new FinancialCalKeyPad();
    }

    @Override
    public Display createDisplay() {
        return new FinancialCalDisplay();
    }
    
}

class Normal implements CalFactory{

    @Override
    public KeyPad creatKeyPad() {
        return new NormalCalKeyPad();
    }

    @Override
    public Display createDisplay() {
        return new NormalCalDisplay();
    }
    
}