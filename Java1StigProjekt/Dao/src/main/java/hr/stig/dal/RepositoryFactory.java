/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal;

import hr.stig.dal.sql.DataSourceSingleton;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natio
 */
public class RepositoryFactory {

    private static Repository repository;

    private static final String PATH = "/configuration/repository.properties";
    private static final String CLASS_NAME = "CLASS_NAME";
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = DataSourceSingleton.class.getResourceAsStream(PATH);) {
            properties.load(is);
            repository = (Repository) Class
                    .forName(properties.getProperty(CLASS_NAME))
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private RepositoryFactory() {
        throw new RuntimeException();
    }

    public static Repository getRepository() {
        /*
        if (repository == null) {
            repository = new SqlRepository();
        }
         */
        return repository;
    }

}
