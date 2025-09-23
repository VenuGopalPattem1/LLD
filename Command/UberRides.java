package Command;

public class UberRides {
    public static void main(String[] args) {
        RidingService ser=new RidingService();
        Command c1=new RequestRideService(ser, "sangam", "nellore", "vinay");
        Command c2=new CancelRideService(ser, "vinay");
        c1.execute();
        c2.execute();
    }
}
//Receiver
class RidingService{
    public void requestRide(String src,String dst,String passenger){
        System.out.println("Ride requested from "+src+" to "+dst+" for passenger "+passenger);
    }
    public void cancelRide(String passenger){
        System.out.println("Ride cancelled for passenger "+passenger);
    }
}

//Command
interface Command{
    void execute();
}

class RequestRideService implements Command{
    private RidingService service;
    private String pas;
    private String src;
    private String dst;

    public RequestRideService(RidingService service,String src,String dst,String pas){
        this.service=service;
        this.src=src;
        this.dst=dst;
        this.pas=pas;
    }
    public void execute(){
        service.requestRide(src, dst, pas);
    }
}

class CancelRideService implements Command{
    private RidingService service;
    private String pas;

    public CancelRideService(RidingService service,String pas){
        this.service=service;
        this.pas=pas;
    }
    public void execute(){
        service.cancelRide(pas);
    }
}