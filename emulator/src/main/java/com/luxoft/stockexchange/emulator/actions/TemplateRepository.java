package com.luxoft.stockexchange.emulator.actions;

import com.luxoft.stockexchange.emulator.utils.XMLConverter;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class TemplateRepository {

    private Map<String, ActionTemplate> nameToTemplate;

    private String pathToTemplate = "defaultTemplates.xml";

    @PostConstruct
    public void init() throws IOException, JAXBException {
        ActionTemplatesList list = XMLConverter.getInstance().convertFromXMLToObject(pathToTemplate, ActionTemplatesList.class);

        nameToTemplate = new HashMap<>();
        for(ActionTemplate template : list.getActionTemplates()) {
            nameToTemplate.put(template.getName(), template);
        }
    }

    public void setPathToTemplate(String pathToTemplate) {
        this.pathToTemplate = pathToTemplate;
    }

    public Collection<ActionTemplate> getAll() {
        return nameToTemplate.values();
    }

    public ActionTemplate getTemplate(String name) {
        if(!nameToTemplate.containsKey(name)) {
            throw new IllegalArgumentException("Template "+name+" is not configured in system. Please refer to emulator configuration. ");
        }
        return nameToTemplate.get(name);
    }
}
