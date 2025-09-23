package FactoryMethod;
//main class
public class Logger {
    public static void main(String[] args) {
        LoggerFactory factory=new ErrorLoggerFactory();
        ILogger logger=factory.createLogger();
        logger.log();
    }
}

//this is main Integerface
interface ILogger{
    void log();
}

class DebugLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is DEBUG Logger Message");
    }
    
}

class InfoLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is INFO Logger Message");
    }
    
}

class ErrorLogger implements ILogger{
    @Override
    public void log() {
        System.out.println("This is ERROR Logger Message");
    }
    
}

//Factory Interface
interface LoggerFactory{
    ILogger createLogger();
}
//Factory Interface Implemetation classes
class DebugLoggerFactory implements LoggerFactory{
    @Override
    public ILogger createLogger() {
        return new DebugLogger();
    }
    
}
//Factory Interface Implemetation classes
class InfoLoggerFactory implements LoggerFactory{
    @Override
    public ILogger createLogger() {
        return new InfoLogger();
    }
    
}
//Factory Interface Implemetation classes
class ErrorLoggerFactory implements LoggerFactory{
    @Override
    public ILogger createLogger() {
        return new ErrorLogger();
    }
    
}