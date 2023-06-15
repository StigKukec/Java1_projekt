/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.parsers.rss;

import hr.stig.factory.ParserFactory;
import hr.stig.factory.UrlConnectionFactory;
import hr.stig.models.Movie;
import hr.stig.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author natio
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    private static final String EXTENSION = ".jpg";
    private static final String DIRECTORY = "assets";

    private MovieParser() {

    }

    public static List<Movie> parseMovie() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();

        HttpURLConnection connection = UrlConnectionFactory.getHttpURLConnection(RSS_URL);

        try (InputStream is = connection.getInputStream();) {

            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Movie movie = null;
            StartElement startElement = null;
            Optional<Tag> tag = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tag = Tag.from(qName);
                        if (tag.isPresent() && tag.get().equals(Tag.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tag.isPresent() && movie != null) {
                            String data = event.asCharacters().getData().trim();
                            switch (tag.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        data = data.replaceAll("<[^>]+>", "");
                                        movie.setDescription(data);
                                    }
                                }
                                case GENRE -> {
                                    if (!data.isEmpty()) {
                                        movie.setGenres(data);
                                    }
                                }
                                case DURATION -> {
                                    if (!data.isEmpty()) {

                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                }
                                case YEAR -> {
                                    if (!data.isEmpty()) {
                                        movie.setYear(Integer.parseInt(data));
                                    }
                                }

                                case POSTER -> {
                                    if (!data.isEmpty()) {
                                        String url = data;
                                        handlePoster(movie, url);
                                    }
                                }
                                case ACTORS -> {
                                    if (!data.isEmpty()) {
                                        movie.setActors(data);
                                    }
                                }
                                case DIRECTORS -> {
                                    if (!data.isEmpty()) {
                                        movie.setDirectors(data);
                                    }
                                }
                                default ->
                                    throw new AssertionError(tag.get().name());
                            }
                        }
                    }
                }
            }
        }
        return movies;
    }

    private static void handlePoster(Movie movie, String source) throws IOException {

        String extension = source.substring(source.lastIndexOf("."));
        if (extension.length() > 4) {
            extension = EXTENSION;
        }

        String name = UUID.randomUUID() + extension;
        String localPath = DIRECTORY + File.separator + name;
        FileUtils.copyFromUrl(source, localPath);
        movie.setPoster(localPath);
    }

    private enum Tag {
        ITEM("item"),
        TITLE("title"),
        DESCRIPTION("description"),
        GENRE("zanr"),
        DURATION("trajanje"),
        YEAR("godina"),
        POSTER("plakat"),
        ACTORS("glumci"),
        DIRECTORS("redatelj");

        private final String name;

        private Tag(String name) {
            this.name = name;
        }

        public static Optional<Tag> from(String name) {

            for (Tag value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }
}
