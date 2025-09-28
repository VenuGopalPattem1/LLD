package Bridge;

public class UberDemo {
    public static void main(String[] args) {
        NavigationImpl google=new GoogleMpas();
        NavigationImpl apple=new AppleMpas();

        UberEats eats=new UberEats("Paradise");
        eats.setNavigationImpl(google);
        eats.navigateTo("Sangam");
        UberRide ride=new UberRide("Venu");
        ride.setNavigationImpl(apple);
        ride.navigateTo("Nellore");
    }
}
//implementation
interface NavigationImpl{
    public void navigae(String dst);
}

class GoogleMpas implements NavigationImpl{

    @Override
    public void navigae(String dst) {
        System.out.println("using google maps to "+dst);
    }
    
}
class AppleMpas implements NavigationImpl{

    @Override
    public void navigae(String dst) {
        System.out.println("using apple maps to "+dst);
    }
    
}
//abstraction
interface NavigationSys{
    public void navigateTo(String dst);
}

class UberEats implements NavigationSys{
    private String restuarent;
    private NavigationImpl nav;//impl
    public UberEats(String res){
        restuarent=res;
    }

    public void setNavigationImpl(NavigationImpl impl){
        nav=impl;
    }

    @Override
    public void navigateTo(String dst) {
        System.out.println("order succesfully deliverd from "+restuarent+" using");
        nav.navigae(dst);
    }
    
}

class UberRide implements NavigationSys{
    private String customer;
    private NavigationImpl nav;
    public UberRide(String res){
        customer=res;
    }

    public void setNavigationImpl(NavigationImpl impl){
        nav=impl;
    }

    @Override
    public void navigateTo(String dst) {
        System.out.println("ride succesfully completed for "+customer+" using");
        nav.navigae(dst);
    }
}