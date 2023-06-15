/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public class Director extends Person {

    private int idDirector;

    public int getIdDirector() {
        return idDirector;
    }

    public Director() {

    }

    public Director(int idDirector, String firstName, String lastName) {
        this(firstName, lastName);
        this.idDirector = idDirector;
    }

    public Director(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
