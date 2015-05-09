package com.luxoft.stockexchange.emulator.actions;

import com.luxoft.stockexchange.emulator.entities.FixMessage;

import java.util.Map;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class FixActivityGenerator {

    private TemplateRepository templateRepository;


    public FixMessage createMessage(String templateName, FixMessage parentMessage, Map<Integer, Object> overrideDetails) {
        return templateRepository.getTemplate(templateName).create(parentMessage,overrideDetails);
    }

    public FixMessage createMessage(String templateName, Map<Integer, Object> overrideDetails) {
        return createMessage(templateName, null,overrideDetails);
    }

    public FixMessage createMessage(String templateName, FixMessage parentMessage) {
        return createMessage(templateName, parentMessage,null);
    }

    public FixMessage createMessage(String templateName) {
        return createMessage(templateName, null,null);
    }


}
