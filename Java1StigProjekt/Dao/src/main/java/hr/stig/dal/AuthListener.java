/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal;

import javax.swing.JLabel;

/**
 *
 * @author natio
 */
public interface AuthListener {
    void onAuthSuccess(String username);
    void displayUsername(String username,JLabel label);
}
