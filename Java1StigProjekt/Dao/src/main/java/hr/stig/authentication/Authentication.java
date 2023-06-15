/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.util.validations;

import hr.stig.models.Account;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author natio
 */
public class Authentication {

    private Authentication() {
        throw new RuntimeException();
    }
    private static String username;
    private static boolean userType = false;

    public static boolean loginValid(List<Account> accounts, String username, String password) throws Exception {
        Authentication.username = "";
        Authentication.username = username;
        for (Account acc : accounts) {
            userType = acc.isAdministrator();
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void displayUsername(JLabel label, boolean isAdministrator) {
        String type = isAdministrator ? "Administrator" : "Member";
        label.setText("Signed in as: " + username + " || " + "Type: " + type);
    }

    public static boolean isAdministrator() {
        return userType;
    }

}
