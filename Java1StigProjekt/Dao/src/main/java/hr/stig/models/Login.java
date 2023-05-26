/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author natio
 */
public class Login {

    private static final String DIR = "C:\\Users\\natio\\OneDrive\\Dokumenti\\Stig_Kukec_Algebra\\Java1\\Projekti\\Administratori.txt";

    public Login() {
    }

    private int idAccount;
    private String username;
    private String password;
    private boolean administrator;

    public Login(int idAccount, String username, String password, boolean administrator) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
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
        final Login other = (Login) obj;
        return Objects.equals(this.username, other.username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    /*
    public List<Login> ParseFormFileLine() throws IOException {
        List<Login> l = new ArrayList<Login>();
        BufferedReader bf = new BufferedReader(new FileReader(DIR));
        String line;
        while ((line = bf.readLine()) != null) {
            String[] details = line.split(",");
            Login lg = new Login(details[0], details[1]);
            l.add(lg);
        }
        bf.close();
        return l;

    }
     */
}
