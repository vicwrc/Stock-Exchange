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
	public static void main(String[] args) throws Exception {
        FixEmulator emulator = new FixEmulator();

        emulator.start();

        emulator.client.send(emulator.generator.createMessage("NewOrderSingle"), emulator.client.getSessions().get("BANZAI1"));
		
		System.in.read();

        emulator.stop();
	}
}
