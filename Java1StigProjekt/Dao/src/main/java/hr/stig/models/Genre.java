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
    ACTION("akcija"),
    ANIMATION("animirani"),
    DOCUMENTARY("dokumentarac"),
    CRIME("kriminalistički"),
    DRAMA("drama"),
    ADVENTURE("avantura"),
    COMEDY("komedija"),
    FANTASY("fantazija"),
    HORROR("horor"),
    MUSICAL("muzički"),
    MISTERY("misterija"),
    ROMANCE("romantičan"),
    THRILLER("triler"),
    WESTERN("western"),
    WAR("ratni"),
    ROMANTIC_COMEDY("romnantična komedija");

    private final String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public static Genre from(String genre) {
        for (Genre value : values()) {
            if (value.genre.equals(genre)) {
                return value;
            }
        }
        throw new RuntimeException("Wrong genre!");
    }
}
