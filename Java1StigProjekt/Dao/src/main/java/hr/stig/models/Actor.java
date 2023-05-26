/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public class Actor extends Person{
    private int idActor;
    public Actor(int idActor,String firstName, String lastName) {
        super(firstName, lastName);
        this.idActor = idActor;
    }
    
}
