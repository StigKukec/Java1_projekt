/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal.sql;

import hr.stig.dal.Repository;
import hr.stig.models.Actor;
import hr.stig.models.Director;
import hr.stig.models.Genre;
import hr.stig.models.Login;
import hr.stig.models.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author natio
 */
public class SqlRepository implements Repository {

    private static final String ID_ACCOUNTS = "IDAccounts";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ADMINISTRATOR = "Administrator";
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String GENRE = "Genre";
    private static final String RELEASEYEAR = "ReleaseYear";
    private static final String DURATION = "Duration";
    private static final String MOVIEDESCRIPTION = "MovieDescription";
    private static final String POSTER = "Poster";
    private static final String ID_ACTOR = "IDActor";
    private static final String ID_DIRECTOR = "IDMovieDirector";
    private static final String FIRSTNAME = "FirstName";
    private static final String LASTNAME = "LastName";

    private static final String CREATE_MOVIE = "{CALL createMovie (?,?,?,?,?,?) }";
    private static final String CREATE_ACTOR = "{CALL createActor (?,?) }";
    private static final String CREATE_DIRECTOR = "{CALL createMoviedDirector (?,?) }";
    private static final String UPDATE_MOVIE = "";
    private static final String UPDATE_ACTOR = "";
    private static final String UPDATE_DIRECTOR = "";
    private static final String DELETE_MOVIE = "";
    private static final String DELETE_ACTOR = "";
    private static final String DELETE_DIRECTOR = "";
    private static final String SELECT_MOVIE = "";
    private static final String SELECT_ACTOR = "";
    private static final String SELECT_DIRECTOR = "";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(GENRE, movie.getGenre().getGenre());
            stmt.setInt(RELEASEYEAR, movie.getYear());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setString(MOVIEDESCRIPTION, movie.getDescription());
            stmt.setString(POSTER, movie.getPoster());

            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public int createActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_ACTOR);) {
            stmt.setString(FIRSTNAME, actor.getFirstName());
            stmt.setString(FIRSTNAME, actor.getLastName());

            stmt.executeUpdate();

            return stmt.getInt(ID_ACTOR);
        }

    }

    @Override
    public int createDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_DIRECTOR);) {
            stmt.setString(FIRSTNAME, director.getFirstName());
            stmt.setString(FIRSTNAME, director.getLastName());

            stmt.executeUpdate();

            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(GENRE, movie.getGenre().getGenre());
            stmt.setInt(RELEASEYEAR, movie.getYear());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setString(MOVIEDESCRIPTION, movie.getDescription());
            stmt.setString(POSTER, movie.getPoster());
            stmt.setInt(ID_MOVIE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateActor(int id, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_ACTOR);) {
            stmt.setString(FIRSTNAME, actor.getFirstName());
            stmt.setString(FIRSTNAME, actor.getLastName());
            stmt.setInt(ID_ACTOR, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void updateDirector(int id, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_DIRECTOR);) {
            stmt.setString(FIRSTNAME, director.getFirstName());
            stmt.setString(FIRSTNAME, director.getLastName());
            stmt.setInt(ID_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_MOVIE);) {
            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_ACTOR);) {

            stmt.setInt(ID_ACTOR, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_DIRECTOR);) {

            stmt.setInt(ID_DIRECTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Movie selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_MOVIE);) {
            stmt.setInt(ID_MOVIE, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            Genre.from(rs.getString(GENRE)),
                            rs.getString(MOVIEDESCRIPTION),
                            rs.getInt(DURATION),
                            rs.getInt(RELEASEYEAR),
                            rs.getString(POSTER)
                    );
                }
            }
        }
        return null;
    }

    @Override
    public Actor selectActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACTOR);) {
            stmt.setInt(ID_ACTOR, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(FIRSTNAME),
                            rs.getString(LASTNAME)
                    );
                }
            }
        }
        return null;
    }

    @Override
    public Director selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_DIRECTOR);) {
            stmt.setInt(ID_DIRECTOR, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(FIRSTNAME),
                            rs.getString(LASTNAME)
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_MOVIE); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        Genre.from(rs.getString(GENRE)),
                        rs.getString(MOVIEDESCRIPTION),
                        rs.getInt(DURATION),
                        rs.getInt(RELEASEYEAR),
                        rs.getString(POSTER)
                ));
            }
        }
        return movies;
    }

    @Override
    public List<Actor> selectActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACTOR); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt(ID_ACTOR),
                        rs.getString(FIRSTNAME),
                        rs.getString(LASTNAME)
                ));
            }
        }
        return actors;
    }

    @Override
    public List<Director> selectDirectors() throws Exception {
        List<Director> directors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_DIRECTOR); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                directors.add(new Director(
                        rs.getInt(ID_DIRECTOR),
                        rs.getString(FIRSTNAME),
                        rs.getString(LASTNAME)
                ));
            }
        }
        return directors;
    }

    @Override
    public List<Login> getAccounts() throws Exception {
        List<Login> accounts = new ArrayList<>();
            DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_DIRECTOR);) {
            try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                    accounts.add( new Login(
                            rs.getInt(ID_ACCOUNTS),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getBoolean(ADMINISTRATOR)
                    ));
                }
            }
        }
        return null;
    }

}
