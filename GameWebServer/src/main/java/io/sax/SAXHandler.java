package io.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.ReflectionHelper;

import java.util.Properties;

/**
 * Created by Ageev Evgeny on 25.01.2016.
 */
public class SAXHandler extends DefaultHandler {
    private static String ROOT = "config";
    private String element = null;
    private Properties props = new Properties();
    //private Object object = null;

    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    public void endDocument() throws SAXException {
        System.out.println("End document ");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(!qName.equals(ROOT)){
            element = qName;
            System.out.println("Element: " + qName);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = null;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if(element != null){
            String value = new String(ch, start, length);
            System.out.println(element + " = " + value);
            //ReflectionHelper.setFieldValue(object, element, value);
            //if (element.equals("port")) {
                props.put(element, value);
            //}
        }
    }

    public Object getObject(){
        //return object;
        return props;
    }
}
