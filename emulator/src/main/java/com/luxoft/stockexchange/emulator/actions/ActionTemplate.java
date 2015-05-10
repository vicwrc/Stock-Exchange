package com.luxoft.stockexchange.emulator.actions;

import com.luxoft.stockexchange.emulator.actions.template.FixActivityRow;
import com.luxoft.stockexchange.emulator.entities.FixMessage;
import quickfix.Message;
import quickfix.MessageFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by victorvorontsov on 09.05.15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionTemplate implements Serializable {

    @XmlAttribute
    private String fixVersion;
    @XmlAttribute
    private String msgType;

    @XmlElement(name="FixActivityRow")
    private List<FixActivityRow> activityRowList = new LinkedList<>();

    public List<FixActivityRow> getActivityRowList() {
        return activityRowList;
    }

    public void setActivityRowList(List<FixActivityRow> activityRowList) {
        this.activityRowList = activityRowList;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public void setFixVersion(String fixVersion) {
        this.fixVersion = fixVersion;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public FixMessage create(FixMessage parentMessage, Map<Integer, Object> formData) {
        FixMessage message = new FixMessage(createMessage());

        visit(message, parentMessage,adjustFormData(formData));
        return message;
    }

    protected Map<Integer, Object> adjustFormData(Map<Integer, Object> formData) {
        if(null != formData) {
            formData.remove(Integer.valueOf(35));
            formData.remove(Integer.valueOf(8));
        }
        return formData;
    }

    protected Message createMessage() {
        return new quickfix.DefaultMessageFactory().create(fixVersion,msgType);
    }

    protected void visit(FixMessage currentMessage, FixMessage parentMessage, Map<Integer, Object> formData) {
        for(FixActivityRow row : activityRowList) {
            row.visit(currentMessage,parentMessage,formData);
        }
    }

    @Override
    public String toString() {
        return "ActionTemplate{" +
                "fixVersion='" + fixVersion + '\'' +
                ", msgType='" + msgType + '\'' +
                ", activityRowList=" + activityRowList +
                '}';
    }
}
