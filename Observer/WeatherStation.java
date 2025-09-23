package Observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherReport wr=new WeatherReport();
        Observerr tv=new Telelvision();
        Observerr app=new WeatherApp();
        Observerr news=new NewsChannel();
        wr.add(tv);
        wr.add(app);
        wr.add(news);
        wr.setTemp(30);
        System.out.println("-------------------");
        wr.delete(app);
        wr.setTemp(32);
    }
}

//subject
class WeatherReport{
    private int temp;
    private List<Observerr> li=new ArrayList<>();

    public void setTemp(int temp){
        this.temp=temp;
        notifyAllObserverrs(temp);
    }
    public int getTemp() {
        return temp;
    }
    public void add(Observerr o){
        li.add(o);
    }
    public void delete(Observerr o){
        li.remove(o);
    }
    private void notifyAllObserverrs(int temp){
        for(Observerr o:li){
            o.update(temp);
        }
    }
}

interface Observerr{
    public void update (int temp);
}

class Telelvision implements Observerr{
    public void update(int temp) {
        System.out.println(" current temperature is "+temp);
    }
}

class WeatherApp implements Observerr{
    public void update(int temp) {
        System.out.println(" current temperature is "+temp);
    }
}

class NewsChannel implements Observerr{
    public void update(int temp) {
        System.out.println(" current temperature is "+temp);
    }
}



