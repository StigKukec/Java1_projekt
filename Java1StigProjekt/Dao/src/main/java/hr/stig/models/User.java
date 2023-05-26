/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

/**
 *
 * @author natio
 */
public class User extends Login {
    
    public User(int idAccount, String username, String password, boolean administrator) {
        super(idAccount, username, password, administrator);
    }
    
}
