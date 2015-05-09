package com.luxoft.stockexchange.emulator.actions.template;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.utils.CompiledMVEL;
import quickfix.Field;
import quickfix.FieldMap;
import quickfix.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class FixActivityRow implements Serializable {

    private FixDataLocation location;
    private int tag;
    private Class<?> dataType;
    private String expression;

    public FixDataLocation getLocation() {
        return location;
    }

    public void setLocation(FixDataLocation location) {
        this.location = location;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Class<?> getDataType() {
        return dataType;
    }

    public void setDataType(Class<?> dataType) {
        this.dataType = dataType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    // business logic

    public void visit(FixMessage currentMessage, FixMessage parentMessage, Map<Integer, Object> formData ) {
        // check form data
        if(! formDataApplicable(currentMessage, formData)) {
            evaluateExpression(currentMessage,parentMessage);
        }
    }

    protected void evaluateExpression(FixMessage currentMessage, FixMessage parentMessage) {
        Object dataToSet = CompiledMVEL.executeExpression(expression, Collections.singletonMap("parent",null == parentMessage ? null :parentMessage.getMessage()));
        if(null != dataToSet && ! isValidDataType(dataToSet)) {
            throw new IllegalArgumentException("incorrect evaluation result of expression: ["+ expression+ "] within param : parent = "+ parentMessage.getMessage());
        }
        setTo(currentMessage,dataToSet);
    }

    protected boolean formDataApplicable(FixMessage currentMessage, Map<Integer, Object> formData) {
        if(null == formData) {
            return false;
        }
        Object dataToSet = formData.get(this.tag);
        if(null == dataToSet || ! isValidDataType(dataToSet)) {
            return false;
        }
        setTo(currentMessage,dataToSet);

        return true;
    }

    protected boolean isValidDataType(Object dataToSet) {
        return dataType.isAssignableFrom(dataToSet.getClass());
    }

    protected void setTo(FixMessage currentMessage, Object value) {
        FieldMap fieldMap = getTargetFieldMap(currentMessage.getMessage());
        fieldMap.setField(tag, new Field<Object>(tag,value));
    }

    protected FieldMap getTargetFieldMap(Message message) {
        if(FixDataLocation.HEADER.equals(this.location)) {
            return message.getHeader();
        }
        if(FixDataLocation.TRAILER.equals(this.location)) {
            return message.getTrailer();
        }
        if(FixDataLocation.BODY.equals(this.location)) {
            return message;
        }
        throw new IllegalArgumentException("Can't find appropriate location");
    }
}
