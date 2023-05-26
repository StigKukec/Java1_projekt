/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author natio
 */
public class DataSourceSingleton {

    private static final String SERVER_NAME = "DESKTOP-TC50P59\\SQLEXPRESS2";
    private static final String DATABASE_NAME = "MovieAPP";
    private static final String USER = "sa";
    private static final String PASSWORD = "SQL";
    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        
        return dataSource;
    }

    private DataSourceSingleton() {
    }

}