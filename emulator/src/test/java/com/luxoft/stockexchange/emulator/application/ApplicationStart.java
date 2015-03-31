package com.luxoft.stockexchange.emulator.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionNotFound;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.utils.MessageFactory;

public class ApplicationStart {
	public static void main(String[] args) throws IncorrectTagValue, FieldNotFound, SessionNotFound, Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		System.out.println(context.getBean("ser"));
		
		Application server = (Application) context.getBean("server");
		Application client = (Application) context.getBean("client");
		
		server.start();
		client.start();
		
		FixMessage mes = new FixMessage(MessageFactory.createMessage("Fix4.2", "NewOrderSingle"));
		client.send(mes, client.getSessions().get("BANZAI1"));
		
		System.in.read();
		
		server.stop();
		client.stop();
	}
}
