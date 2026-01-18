package software.ulpgc.project.application;

import software.ulpgc.project.Destop;
import software.ulpgc.project.model.Movie;
import software.ulpgc.project.viewmode.Histogram;
import software.ulpgc.project.viewmode.HistogramBuilder;

import java.util.stream.Stream;

public class Main {
    static void main(String[] args) {
        Stream<Movie> movies = new RemoteMovieLoader(Desarilazer::fromTsv).loadMovies();
        Histogram histogram= HistogramBuilder.create(movies).addTitle("Histogram by genders")
                .addX("Genders")
                .addY("counts")
                .addLegend("Classyfy by genders")
                .classifyMultiple(Movie::genres);
        Destop.create().display(histogram).setVisible(true);
    }
}
