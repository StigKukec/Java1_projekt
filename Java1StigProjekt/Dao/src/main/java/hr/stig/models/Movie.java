/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author natio
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    @XmlTransient
    private int id;
    private String title;
    private String description;
    private Genre genre;
    private int duration;
    private int year;
    private String poster;
    private String actors;
    private String directors;
    private String genres;
    List<Actor> listActors = new ArrayList<>();
    List<Director> listDirectors = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, String genres, String description, int duration, int year, List<Actor> listActors, List<Director> listDirectors, String poster) {
        this(title, genres, description, duration, year, poster);
        this.listActors = listActors;
        this.listDirectors = listDirectors;

    }

    public Movie(String title, String genres, String description, int duration, int year, String actors, String directors, String poster) {
        this(title, genres, description, duration, year, poster);
        this.actors = actors;
        this.directors = directors;
    }

    public Movie(int id, String title, String genres, String description, int duration, int year, String poster) {
        this(title, genres, description, duration, year, poster);
        this.id = id;

    }

    public Movie(String title, String genres, String description, int duration, int year, String poster) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.duration = duration;
        this.year = year;
        this.poster = poster;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.title);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + this.duration;
        hash = 11 * hash + this.year;
        hash = 11 * hash + Objects.hashCode(this.actors);
        hash = 11 * hash + Objects.hashCode(this.directors);
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
        if (this.id != other.id) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.actors, other.actors)) {
            return false;
        }
        return Objects.equals(this.directors, other.directors);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public List<Actor> getActor() {
        if (actors != null) {
            String[] detailsA = actors.split(",");
            int numberOfActors = detailsA.length;
            for (int i = 0; i < numberOfActors; i++) {
                if (i == 0) {

                    String firstName = detailsA[i].substring(0, detailsA[i].lastIndexOf(" "));
                    String lastName = detailsA[i].substring(detailsA[i].lastIndexOf(" "));
                    listActors.add(new Actor(firstName, lastName));
                } else {
                    String fullName = detailsA[i].trim();
                    String firstName = fullName.substring(0, fullName.lastIndexOf(" "));
                    String lastName = fullName.substring(fullName.lastIndexOf(" ")).trim();
                    listActors.add(new Actor(firstName, lastName));
                }
            }
        }
        return listActors;
    }

    public List<Director> getDirector() {
        if (directors != null) {
            String[] detailsD = directors.split(",");
            int numberOfDirectors = detailsD.length;
            for (int i = 0; i < numberOfDirectors; i++) {
                if (i == 0) {
                    String firstName = detailsD[i].substring(0, detailsD[i].lastIndexOf(" "));
                    String lastName = detailsD[i].substring(detailsD[i].lastIndexOf(" "));
                    listDirectors.add(new Director(firstName, lastName));
                } else {
                    String fullName = detailsD[i].trim();
                    String firstName = fullName.substring(0, fullName.lastIndexOf(" "));
                    String lastName = fullName.substring(fullName.lastIndexOf(" ")).trim();
                    listDirectors.add(new Director(firstName, lastName));
                }
            }
        }
        return listDirectors;
    }

    public void setListActors(List<Actor> listActors) {
        this.listActors = listActors;
    }

    @Override
    public String toString() {
        return getTitle() + "," + getYear();
    }

}
