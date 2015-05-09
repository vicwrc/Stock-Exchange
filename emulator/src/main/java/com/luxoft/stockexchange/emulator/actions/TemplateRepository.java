package com.luxoft.stockexchange.emulator.actions;

import java.util.Map;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class TemplateRepository {

    private Map<String, ActionTemplate> nameToTemplate;

    public void init() {
        // todo - load config using JAXB

    }

    public ActionTemplate getTemplate(String name) {
        if(!nameToTemplate.containsKey(name)) {
            throw new IllegalArgumentException("Template "+name+" is not configured in system. Please refer to emulator configuration. ");
        }
        return nameToTemplate.get(name);
    }
}
