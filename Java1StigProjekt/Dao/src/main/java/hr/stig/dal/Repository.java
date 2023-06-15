/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal;

import hr.stig.models.Actor;
import hr.stig.models.Director;
import hr.stig.models.Account;
import hr.stig.models.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author natio
 */
public interface Repository {

    void createMovie(Movie movie) throws Exception;

    void createActor(Actor actor) throws Exception;

    void insertActorInMovie(Movie movie, Actor actor) throws Exception;

    void createDirector(Director director) throws Exception;

    void insertDirectorInMovie(Movie movie, Director director) throws Exception;

    void createAccount(Account account) throws Exception;

    void uploadMovies(Movie movie) throws Exception;

    void updateMovie(int id, Movie movie) throws Exception;

    void updateActor(int id, Actor actor) throws Exception;

    void updateDirector(int id, Director director) throws Exception;

    void updateAccount(int id, Account account) throws Exception;

    void deleteMovie(int id) throws Exception;

    void deleteActor(int id) throws Exception;

    void deleteDirector(int id) throws Exception;

    void deleteAccount(int id) throws Exception;

    void deleteAllMovies() throws Exception;

    void deleteAllActors() throws Exception;

    void deleteAllMovieDirectors() throws Exception;

    Optional selectMovie(int id) throws Exception;

    Optional selectActor(int id) throws Exception;

    Optional selectDirector(int id) throws Exception;

    Optional selectAccount(int id) throws Exception;

    List<Movie> selectMovies() throws Exception;

    List<Movie> selectAllMovies() throws Exception;

    List<Actor> selectActors() throws Exception;

    List<Director> selectDirectors() throws Exception;

    List<Account> getAccounts() throws Exception;
}
