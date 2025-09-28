package Proxy;

public class ImageDemo {
    public static void main(String[] args) {
        Image image1=new ProxyImage("venu.jpeg");
        Image image2=new ProxyImage("vamsi.jpeg");

        image1.display();
        System.out.println("");
        image1.display();
        System.out.println("");
        image2.display();
        System.out.println("");
        image2.display();
    }
}
interface Image{
    void display();
}

class RealImage implements Image{
    private String name;

    public RealImage(String name){
        this.name=name;
        loadFromDisk();
    }
    @Override
    public void display() {
        System.out.println("Displaying "+name);
    }
    private void loadFromDisk(){
        System.out.println("Loading "+name);
    }
}

class ProxyImage implements Image{
    private RealImage realImage;
    private String name;

    public ProxyImage(String name){
        this.name=name;
    }

    @Override
    public void display() {
        if(realImage==null){
            realImage=new RealImage(name);
        }
        realImage.display();
    }
    
}