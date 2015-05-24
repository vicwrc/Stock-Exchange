package com.luxoft.stockexchange.emulator.actions.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * Created by victorvorontsov on 24.05.15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FixRowDataTemplate implements Serializable {

    @XmlAttribute
    private Integer tag;
    private Class dataType;
    @XmlAttribute
    private String name;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Class getDataType() {
        return dataType;
    }

    public void setDataType(Class dataType) {
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FixRowDataTemplate{" +
                "tag=" + tag +
                ", dataType=" + dataType +
                ", name='" + name + '\'' +
                '}';
    }
}
