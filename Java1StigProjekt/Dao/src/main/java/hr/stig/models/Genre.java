/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;


/**
 *
 * @author natio
 */
public enum Genre {
    ACTION("Action"),
    ANIMATION("Animation"),
    DOCUMENTARY("Documentary"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MISTERY("Mistery"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private final String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
    
    public static Genre from(String genre){
        for (Genre value : values()) {
            if (value.genre.equals(genre)) {
                return value;
            }
        }
        throw new RuntimeException("Wrong genre!");
    }
}
