package com.luxoft.stockexchange.emulator.utils;

import java.util.Date;

import quickfix.FieldNotFound;
import quickfix.Message;

//TODO Raw state
public final class MessageFactory {
	private static volatile MessageFactory instance;
	
	private MessageFactory(){
		
	}
	
	public static quickfix.Message createMessage(String version, String type) throws Exception {
		quickfix.Message message = null;
		
		switch (version) {
		case "Fix4.2" : message = fix42Orders(type); break;
		default : throw new Exception("");
		}
		
		return message;
	}
	
	public static MessageFactory getInstance() {
		MessageFactory local = instance;
		
		if (local == null) {
			synchronized(MessageFactory.class) {
				local = instance;
				if (local == null) {
					instance = local = new MessageFactory();
				}
			}
		}
		
		return local;
	}
	
	private static Message fix42Orders(String type) throws Exception {
		Message message = null;
		switch (type) {
		    case "NewOrderSingle" : message = newOrderSingle(); break;
		    default : throw new Exception("");
		}
		
		return message;
	}
	
	private static Message newOrderSingle() throws FieldNotFound {
		System.out.println("\n\n\nSending\n\n");
		quickfix.fix42.NewOrderSingle order = new quickfix.fix42.NewOrderSingle();
		// order.setField(field);
		// symbol
		order.setString(55, "rabotai");
		// HandlInst
		order.setChar(21, '1');
		// side
		order.setChar(54, '1');
		// ordertype
		order.setChar(40, '2');
		// orderqty
		order.setDouble(38, 11);
		// transacttime
		order.setUtcTimeStamp(60, new Date());
		// clorid
		order.setString(11, "123");
		// price
		order.setDouble(44, 11);

		return order;
	}
}
