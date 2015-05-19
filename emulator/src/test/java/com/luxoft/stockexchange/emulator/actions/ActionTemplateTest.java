package com.luxoft.stockexchange.emulator.actions;

import com.luxoft.stockexchange.emulator.actions.template.FixActivityRow;
import com.luxoft.stockexchange.emulator.actions.template.FixDataLocation;
import com.luxoft.stockexchange.emulator.utils.XMLConverter;
import org.junit.Test;

import java.math.BigDecimal;

public class ActionTemplateTest {


    public static ActionTemplatesList createConfigExample() {
        ActionTemplatesList list = new ActionTemplatesList();

        ActionTemplate template1 = new ActionTemplate();
        template1.setFixVersion("FIX.4.2");
        template1.setMsgType("D");
        template1.getActivityRowList().add(new FixActivityRow(FixDataLocation.BODY, 38, BigDecimal.class,"new java.math.BigDecimal(\"123\")"));
        template1.getActivityRowList().add(new FixActivityRow(FixDataLocation.BODY, 38, String.class,"new java.math.BigDecimal(\"333\")"));

        list.getActionTemplates().add(template1);

        return list;
    }


    @Test
    public void testGenerateData() throws Exception {
        //XMLConverter.getInstance().convertFromObjectToXML(createConfigExample(), "template.xml");

        System.out.println(XMLConverter.getInstance().convertFromXMLToObject("defaultTemplates.xml",ActionTemplatesList.class));
        System.out.println(createConfigExample());
    }
}