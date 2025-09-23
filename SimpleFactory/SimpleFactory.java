package SimpleFactory;


//this is main method where client access the Factory class to get the object
public class SimpleFactory {
    public static void main(String[] args) {
        ILogger logger1=Factory.getLogger("INFO");
        logger1.log();
        ILogger logger2=Factory.getLogger("ERROR");
        logger2.log();
        ILogger logger3=Factory.getLogger("DEBUG");
        logger3.log();
    }
}

//this is main Integerface
interface ILogger {
    void log();
    
}

//this is DEBUG Logger implemetation class
class DebugLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is DEBUG Logger Message");
    }
    
}

//this is INFO Logger implemetation class
class InfoLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is INFO Logger Message");
    }
    
}

//this is Error Logger implemetation class
class ErrorLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is ERROR Logger Message");
    }
    
}


//This is main Factory class Resopnsible for Object creation without
//exposiong the instantiation logic to the client
class Factory{
    public static ILogger getLogger(String s){
        if(s.equals("DEBUG")){
            return new DebugLogger();
        }else if(s.equals("INFO")){
            return new InfoLogger();
        }else if(s.equals("ERROR")){
            return new ErrorLogger();
        }
        else{
            return null;
        }
    }
}