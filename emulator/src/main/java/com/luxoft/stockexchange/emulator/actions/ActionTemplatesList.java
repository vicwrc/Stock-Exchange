package com.luxoft.stockexchange.emulator.actions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by victorvorontsov on 10.05.15.
 */
@XmlRootElement(name="ActionTemplatesList", namespace="ActionTemplatesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionTemplatesList implements Serializable {

    @XmlElement(name="ActionTemplate")
    private List<ActionTemplate> actionTemplates = new LinkedList<>();

    public List<ActionTemplate> getActionTemplates() {
        return actionTemplates;
    }

    public void setActionTemplates(List<ActionTemplate> actionTemplates) {
        this.actionTemplates = actionTemplates;
    }

    @Override
    public String toString() {
        return "ActionTemplatesList{" +
                "actionTemplates=" + actionTemplates +
                '}';
    }
}
