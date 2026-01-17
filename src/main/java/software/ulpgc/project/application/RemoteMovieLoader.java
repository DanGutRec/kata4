package software.ulpgc.project.application;

import software.ulpgc.project.io.MovieLoader;
import software.ulpgc.project.model.Movie;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {
    private final Function<String, Movie> desarilazer;

    public RemoteMovieLoader(Function<String, Movie> desarilazer) {
        this.desarilazer = desarilazer;
    }

    @Override
    public Stream<Movie> loadMovies() {
        try {
            return loadFrom(new URL("https://datasets.imdbws.com/title.basics.tsv.gz"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Movie> loadFrom(URL url) throws IOException {
        return loadFrom(url.openConnection());
    }

    private Stream<Movie> loadFrom(URLConnection urlConnection) throws IOException {
        return loadFrom(unzip(urlConnection.getInputStream())) ;
    }

    private Stream<Movie> loadFrom(InputStream is) throws IOException {
        return loadFrom(toReader(is));
    }

    private Stream<Movie> loadFrom(BufferedReader reader) throws IOException {
        return reader.lines().skip(1).map(desarilazer);
    }

    private BufferedReader toReader(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }
}
