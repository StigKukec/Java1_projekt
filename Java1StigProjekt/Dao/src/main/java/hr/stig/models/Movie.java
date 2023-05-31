/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public class Movie {

    private int id;
    private String title;
    private String description;
    private Genre genre;
    private int duration;
    private int year;
    private String poster;
    private String actors;
    private String directors;

    public Movie( String title, Genre genre, String description, int duration, int year, String actors, String directors, String poster) {
        this( title, genre, description, duration, year, poster);
        this.actors = actors;
        this.directors = directors;
    }

    public Movie(int id, String title, Genre genre, String description, int duration, int year, String poster) {
        this(title, genre, description, duration, year, poster);
        this.id = id;

    }

    public Movie(String title, Genre genre, String description, int duration, int year, String poster) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.year = year;
        this.poster = poster;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        return this.id == other.id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public String getActors() {
        return actors;
    }

    public String getDirectors() {
        return directors;
    }
    

}
