package software.ulpgc.project.io;

import software.ulpgc.project.model.Movie;

import java.util.stream.Stream;

public interface MovieLoader {
    Stream<Movie> loadMovies();
}
