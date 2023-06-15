/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.factory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author natio
 */
public class UrlConnectionFactory {

    private static final int REQUEST_TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String USER_AGENT = "User-Agent";
    private static final String MOZZILA = "Mozzila/5.0";
    
    public static HttpURLConnection getHttpURLConnection(String path) throws MalformedURLException, IOException{
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(REQUEST_TIMEOUT);
        connection.setConnectTimeout(REQUEST_TIMEOUT);
        connection.setRequestMethod(REQUEST_METHOD);
        connection.addRequestProperty(USER_AGENT, MOZZILA);
        return connection;
    }
    
    public UrlConnectionFactory() {
    }

}
