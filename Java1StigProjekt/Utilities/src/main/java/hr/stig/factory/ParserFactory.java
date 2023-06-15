/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.stig.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author natio
 */
public class ParserFactory {

    private ParserFactory() {
    }
    public static XMLEventReader createStaxParser(InputStream is) throws XMLStreamException{
        
        XMLInputFactory factory = XMLInputFactory.newFactory();
        
        return factory.createXMLEventReader(is);
    }
    
}
