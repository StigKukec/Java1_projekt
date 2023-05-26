/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal;

import hr.stig.models.Actor;
import hr.stig.models.Director;
import hr.stig.models.Login;
import hr.stig.models.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author natio
 */
public interface Repository {

    int createMovie(Movie movie) throws Exception;

    int createActor(Actor actor) throws Exception;

    int createDirector(Director director) throws Exception;

    void updateMovie(int id, Movie movie) throws Exception;

    void updateActor(int id, Actor actor) throws Exception;

    void updateDirector(int id, Director director) throws Exception;

    void deleteMovie(int id) throws Exception;

    void deleteActor(int id) throws Exception;

    void deleteDirector(int id) throws Exception;

    Movie selectMovie(int id) throws Exception;

    Actor selectActor(int id) throws Exception;

    Director selectDirector(int id) throws Exception;

    List<Movie> selectMovies() throws Exception;

    List<Actor> selectActors() throws Exception;

    List<Director> selectDirectors() throws Exception;

    List<Login> getAccounts() throws Exception;
}
