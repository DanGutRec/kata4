package software.ulpgc.project.viewmode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class  Histogram<T extends Comparable<T>> implements Iterable<Histogram.Mold<T>> {
    @Override
    public Iterator<Mold<T>> iterator() {
        return new  Iterator<Mold<T>>() {
            private Iterator<T> iterator=values.keySet().iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Mold<T> next() {
                T item = iterator.next();
                return new Mold<>(item,count(item));
            }
        };
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
    public record Mold<T extends Comparable<T>>(T key, int value){}

}
