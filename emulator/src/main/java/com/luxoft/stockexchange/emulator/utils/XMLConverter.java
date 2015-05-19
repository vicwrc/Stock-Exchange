package com.luxoft.stockexchange.emulator.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.luxoft.stockexchange.emulator.actions.ActionTemplatesList;

import java.io.*;
import java.net.URL;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class XMLConverter {


    private static XMLConverter converter;

    public static synchronized XMLConverter getInstance() {
        if (null == converter) {
            converter = new XMLConverter();
        }
        return converter;
    }

    public void convertFromObjectToXML(Object object, String filepath)
            throws IOException, JAXBException {

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filepath);
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, os);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public Object convertFromXMLToObject(String xmlfile, Class clazz) throws IOException, JAXBException {
        InputStream is = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
             is = open(xmlfile);
            return unmarshaller.unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


    private InputStream open(String path) {
        try{
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            return XMLConverter.class.getClassLoader().getResourceAsStream(path);
        }
    }

}
