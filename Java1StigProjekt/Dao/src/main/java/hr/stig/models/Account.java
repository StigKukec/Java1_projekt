/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

import java.util.Objects;

/**
 *
 * @author natio
 */
public class Account {

    private int idAccount;
    private String username;
    private String password;
    private UserType userType;
    private boolean administrator;

    public Account(int idAccount, String username, String password, UserType userType) {
        this(username, password, userType);
        this.idAccount = idAccount;
    }

    public Account(int idAccount, String username, String password, boolean administrator) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
    }

    public Account(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.username);
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
        final Account other = (Account) obj;
        return Objects.equals(this.username, other.username);
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isAdministrator() {
        return administrator;
    }

}
