package Adapter;

public class JsonExchange {
    public static void main(String[] args) {
        String s="Data in xml format";
        JsonAnalyticsToll j=new JsonAnalyticsToll();
        j.setJson(s);
        j.analyze();

        AnalyticTool a=new AnalyticToolImpl(s);
        a.analyze();
    }
}
// Adaptee
class JsonAnalyticsToll{
    private String json;
    public void setJson(String s){
        json=s;
    }

    public void analyze(){
        if(json.contains("json")){
            System.out.println("this is json data");
        }else{
            System.out.println("this is not json data");
        }
    }
}
// Target
interface AnalyticTool {
    public void analyze();
}
// Adapter
class AnalyticToolImpl implements AnalyticTool{
    private JsonAnalyticsToll jsonTooTol;

    public AnalyticToolImpl(String x){
        System.out.println("Parsing data from xml to json format");
        jsonTooTol=new JsonAnalyticsToll();
        x=x+"json";
        jsonTooTol.setJson(x);
    }

    @Override
    public void analyze() {
        jsonTooTol.analyze();
    }
}