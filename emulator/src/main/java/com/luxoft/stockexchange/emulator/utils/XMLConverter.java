package com.luxoft.stockexchange.emulator.utils;

import com.luxoft.stockexchange.emulator.actions.ActionTemplatesList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

        FileInputStream is = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            is = new FileInputStream(xmlfile);
            return unmarshaller.unmarshal(new StreamSource(is));
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

}
