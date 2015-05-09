package com.luxoft.stockexchange.emulator.application;

import com.luxoft.stockexchange.emulator.FixEmulator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionNotFound;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.utils.MessageFactory;

public class ApplicationStart {
	public static void main(String[] args) throws IncorrectTagValue, FieldNotFound, SessionNotFound, Exception {
        FixEmulator emulator = new FixEmulator();

        emulator.start();

		FixMessage mes = new FixMessage(MessageFactory.createMessage("Fix4.2", "NewOrderSingle"));
        emulator.client.send(mes, emulator.client.getSessions().get("BANZAI1"));
		
		System.in.read();

        emulator.stop();
	}
}
