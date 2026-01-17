package software.ulpgc.project.viewmode;

import software.ulpgc.project.model.Movie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Movie> movies;
    private final Map<String,String> labels;

    private HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies;
        this.labels = new HashMap<>();
    }
    public static HistogramBuilder create(Stream<Movie> movies) {
        return new HistogramBuilder(movies);
    }
    public HistogramBuilder addTitle(String title) {
        labels.put("title", title);
        return this;
    }
    public HistogramBuilder addX(String x) {
        labels.put("x", x);
        return this;
    }
    public HistogramBuilder addY(String y) {
        labels.put("y", y);
        return this;
    }
    public HistogramBuilder addLegend(String legend) {
        labels.put("legend", legend);
        return this;
    }
    public <T> Histogram classifySingular(Function<Movie,T> function){
        Histogram<T> histogram = new Histogram<>(labels);
        movies.map(function::apply).forEach(histogram::add);
        return histogram;
    }
    public <T> Histogram classifyMultiple(Function<Movie,T[]> function){
        Histogram<Object> histogram = new Histogram<>(labels);
        movies.flatMap(movie -> Arrays.stream(function.apply(movie))).forEach(histogram::add);
        return histogram;
    }
}
