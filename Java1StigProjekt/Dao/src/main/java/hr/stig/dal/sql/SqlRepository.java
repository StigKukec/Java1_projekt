/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal.sql;

import hr.stig.dal.Repository;
import hr.stig.models.Actor;
import hr.stig.models.Director;
import hr.stig.models.Account;
import hr.stig.models.Movie;
import hr.stig.models.UserType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author natio
 */
public class SqlRepository implements Repository {

    private static final String ID_ACCOUNT = "IDAccount";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ADMINISTRATOR = "Administrator";
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String GENRE = "Genre";
    private static final String RELEASEYEAR = "ReleaseYear";
    private static final String DURATION = "Duration";
    private static final String MOVIEDESCRIPTION = "MovieDescription";
    private static final String ACTOR = "Actors";
    private static final String DIRECTOR = "Directors";
    private static final String POSTER = "Poster";
    private static final String ID_ACTOR = "IDActor";
    private static final String ID_DIRECTOR = "IDMovieDirector";
    private static final String FIRSTNAME = "FirstName";
    private static final String LASTNAME = "LastName";

    private static final String CREATE_MOVIE = "{CALL createMovie (?,?,?,?,?,?) }";
    private static final String CREATE_ACTOR = "{CALL createActor (?,?) }";
    private static final String CREATE_DIRECTOR = "{CALL createMovieDirector (?,?) }";
    private static final String CREATE_MOVIE_WITH_ID = "{CALL createMovieWithId (?,?,?,?,?,?,?) }";
    private static final String CREATE_ACTOR_WITH_ID = "{CALL createActorWithId (?,?,?) }";
    private static final String CREATE_DIRECTOR_WITH_ID = "{CALL createMovieDirectorWithId (?,?,?) }";
    private static final String CREATE_ACCOUNT = "{CALL createAccount (?,?,?) }";
    private static final String INSERT_ACTOR_IN_MOVIE = "{CALL insertActorInMovie(?,?) }";
    private static final String INSERT_DIRECTOR_IN_MOVIE = "{CALL insertDirectorInMovie (?,?)}";
    private static final String INSERT_DIRECTOR_IN_MOVIE_BY_NAME = "{CALL insertDirectorInMovieByName (?,?,?)}";
    private static final String INSERT_ACTOR_IN_MOVIE_BY_NAME = "{CALL insertActorInMovieByName (?,?,?)}";
    private static final String UPDATE_MOVIE = "{CALL updateMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_ACTOR = "{CALL updateActor (?,?,?) }";
    private static final String UPDATE_DIRECTOR = "{CALL updateMovieDirector (?,?,?) }";
    private static final String UPDATE_ACCOUNT = "{CALL updateAccount (?,?,?,?) }";
    private static final String DELETE_MOVIE = "{CALL deleteMovie (?) }";
    private static final String DELETE_ALL_MOVIES = "{CALL deleteAllMovies  }";
    private static final String DELETE_ALL_ACTORS = "{CALL deleteAllActors  }";
    private static final String DELETE_ALL_MOVIEDIRECTORS = "{CALL deleteAllMovieDirectors  }";
    private static final String DELETE_ACTOR = "{CALL deleteActor (?) }";
    private static final String DELETE_DIRECTOR = "{CALL deleteMovieDirector (?) }";
    private static final String DELETE_ACCOUNT = "{CALL deleteAccount (?) }";
    private static final String SELECT_MOVIE = "{CALL selectMovie (?) }";
    private static final String SELECT_ACTOR = "{CALL selectActor (?) }";
    private static final String SELECT_DIRECTOR = "{CALL selectMovieDirector (?) }";
    private static final String SELECT_ACCOUNT = "{CALL selectAccount (?) }";
    private static final String SELECT_MOVIES = "{CALL selectMovies  }";
    private static final String SELECT_FULL_MOVIE_STATISTIC = "{CALL selectFullMovieStatistic  }";
    private static final String SELECT_ACTORS = "{CALL selectActors  }";
    private static final String SELECT_DIRECTORS = "{CALL selectMovieDirectors  }";
    private static final String SELECT_ACCOUNTS = "{ CALL selectAccounts }";

    @Override
    public void createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(MOVIEDESCRIPTION, movie.getDescription());
            stmt.setString(GENRE, movie.getGenres());
            // stmt.setString(GENRE, movie.getGenre().getGenre());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(RELEASEYEAR, movie.getYear());
            stmt.setString(POSTER, movie.getPoster());

            stmt.executeUpdate();
        }
    }

    @Override
    public void createActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_ACTOR);) {
            stmt.setString(FIRSTNAME, actor.getFirstName());
            stmt.setString(LASTNAME, actor.getLastName());

            stmt.executeUpdate();
        }

    }

    @Override
    public void createDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_DIRECTOR);) {
            stmt.setString(FIRSTNAME, director.getFirstName());
            stmt.setString(LASTNAME, director.getLastName());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(GENRE, movie.getGenres());
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
            stmt.setString(LASTNAME, actor.getLastName());
            stmt.setInt(ID_ACTOR, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateDirector(int id, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_DIRECTOR);) {
            stmt.setString(FIRSTNAME, director.getFirstName());
            stmt.setString(LASTNAME, director.getLastName());
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
    public Optional selectMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_MOVIE);) {
            stmt.setInt(ID_MOVIE, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(GENRE),
                            rs.getString(MOVIEDESCRIPTION),
                            rs.getInt(DURATION),
                            rs.getInt(RELEASEYEAR),
                            rs.getString(POSTER)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional selectActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACTOR);) {
            stmt.setInt(ID_ACTOR, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(FIRSTNAME),
                            rs.getString(LASTNAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional selectDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_DIRECTOR);) {
            stmt.setInt(ID_DIRECTOR, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(FIRSTNAME),
                            rs.getString(LASTNAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_MOVIES); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(GENRE),
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
    public List<Movie> selectAllMovies() throws Exception {
        List<Movie> allMovies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_FULL_MOVIE_STATISTIC); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                allMovies.add(new Movie(
                        rs.getString(TITLE),
                        rs.getString(GENRE),
                        rs.getString(MOVIEDESCRIPTION),
                        rs.getInt(DURATION),
                        rs.getInt(RELEASEYEAR),
                        rs.getString(ACTOR),
                        rs.getString(DIRECTOR),
                        rs.getString(POSTER))
                );
            }
        }

        return allMovies;
    }

    @Override
    public List<Actor> selectActors() throws Exception {
        List<Actor> actors = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACTORS); ResultSet rs = stmt.executeQuery();) {
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
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
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
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACCOUNTS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt(ID_ACCOUNT),
                        rs.getString(USERNAME),
                        rs.getString(PASSWORD),
                        rs.getBoolean(ADMINISTRATOR)
                ));
            }
        }
        return accounts;
    }

    @Override
    public void createAccount(Account account) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_ACCOUNT);) {
            stmt.setString(USERNAME, account.getUsername());
            stmt.setString(PASSWORD, account.getPassword());
            stmt.setInt(ADMINISTRATOR, account.getUserType().getUserType());

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateAccount(int id, Account account) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(UPDATE_ACCOUNT);) {
            stmt.setInt(ID_ACCOUNT, id);
            stmt.setString(USERNAME, account.getUsername());
            stmt.setString(PASSWORD, account.getPassword());
            stmt.setInt(ADMINISTRATOR, account.getUserType().getUserType());

            stmt.executeUpdate();
        }

    }

    @Override
    public void deleteAccount(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_ACCOUNT);) {
            stmt.setInt(ID_ACCOUNT, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional selectAccount(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(SELECT_ACCOUNT);) {
            stmt.setInt(ID_ACCOUNT, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Account(
                            rs.getInt(ID_ACCOUNT),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            UserType.from(rs.getInt(ADMINISTRATOR))));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void uploadMovies(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        List<Director> directors = movie.getDirector();
        List<Actor> actors = movie.getActor();
        List<Integer> genKeyActor = new ArrayList<>();
        List<Integer> genKeyDirector = new ArrayList<>();

        int genKeyMovie = createMovieid(movie);
        for (Director director : directors) {
            if (director != null) {
                try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmtd = sqlConnection.prepareCall(CREATE_DIRECTOR_WITH_ID);) {

                    stmtd.setString(FIRSTNAME, director.getFirstName());
                    stmtd.setString(LASTNAME, director.getLastName());
                    stmtd.registerOutParameter(ID_DIRECTOR, Types.INTEGER);

                    stmtd.executeUpdate();
                    genKeyDirector.add(stmtd.getInt(ID_DIRECTOR));

                }
            }
        }
        for (Actor actor : actors) {
            if (actor != null) {
                try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmta = sqlConnection.prepareCall(CREATE_ACTOR_WITH_ID);) {

                    stmta.setString(FIRSTNAME, actor.getFirstName());
                    stmta.setString(LASTNAME, actor.getLastName());
                    stmta.registerOutParameter(ID_ACTOR, Types.INTEGER);
                    stmta.executeUpdate();
                    genKeyActor.add(stmta.getInt(ID_ACTOR));
                }
            }
        }

        for (Integer actor : genKeyActor) {
            if (genKeyMovie != 0 && actor != 0) {
                try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmtam = sqlConnection.prepareCall(INSERT_ACTOR_IN_MOVIE)) {
                    stmtam.setInt(ID_MOVIE, genKeyMovie);
                    stmtam.setInt(ID_ACTOR, actor);
                    stmtam.executeUpdate();
                }
            }
        }

        for (Integer director : genKeyDirector) {
            if (genKeyMovie != 0 && director != 0) {
                try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmtdm = sqlConnection.prepareCall(INSERT_DIRECTOR_IN_MOVIE)) {
                    stmtdm.setLong(ID_MOVIE, genKeyMovie);
                    stmtdm.setLong(ID_DIRECTOR, director);
                    stmtdm.executeUpdate();
                }
            }
        }
    }

    public int createMovieid(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(CREATE_MOVIE_WITH_ID);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(MOVIEDESCRIPTION, movie.getDescription());
            stmt.setString(GENRE, movie.getGenres().toLowerCase());
            // stmt.setString(GENRE, movie.getGenre().getGenre());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(RELEASEYEAR, movie.getYear());
            stmt.setString(POSTER, movie.getPoster());

            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void deleteAllMovies() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_ALL_MOVIES);) {
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAllActors() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_ALL_ACTORS);) {
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteAllMovieDirectors() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(DELETE_ALL_MOVIEDIRECTORS);) {
            stmt.executeUpdate();
        }
    }

    @Override
    public void insertActorInMovie(Movie movie, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(INSERT_ACTOR_IN_MOVIE_BY_NAME)) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(FIRSTNAME, actor.getFirstName());
            stmt.setString(LASTNAME, actor.getLastName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void insertDirectorInMovie(Movie movie, Director director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();

        try (Connection sqlConnection = dataSource.getConnection(); CallableStatement stmt = sqlConnection.prepareCall(INSERT_DIRECTOR_IN_MOVIE_BY_NAME)) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(FIRSTNAME, director.getFirstName());
            stmt.setString(LASTNAME, director.getLastName());
            stmt.executeUpdate();

        }
    }

}
