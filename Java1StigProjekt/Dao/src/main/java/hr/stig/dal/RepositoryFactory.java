/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal;

import hr.stig.dal.sql.SqlRepository;


/**
 *
 * @author natio
 */
public class RepositoryFactory {

    private static Repository instance;
    private RepositoryFactory() {
        throw new RuntimeException();
    }
    
    public static Repository getRepository() {
        if (instance == null) {
            instance =  new SqlRepository();
        }
        return instance;
    }
}
