package software.ulpgc.project.viewmode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class  Histogram<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return values.keySet().iterator();
    }

    private final Map<T,Integer> values;
    private final Map<String,String> labels;

    public Histogram( Map<String, String> labels) {
        this.values = new HashMap<>();
        this.labels = labels;
    }
    public String getX(){return labels.getOrDefault("x","");}
    public String getY(){return labels.getOrDefault("y","");}
    public String getTitle(){return labels.getOrDefault("title","");}
    public String getLegend(){return labels.getOrDefault("legend","");}
    public Integer count(T value){return values.getOrDefault(value,0);}
    public void add(T value){ values.put(value,count(value)+1);}

}
