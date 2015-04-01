package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import com.luxoft.stockexchange.emulator.application.Application;
import com.luxoft.stockexchange.emulator.application.ClientApplication;
import com.luxoft.stockexchange.emulator.application.ServerApplication;

public final class InitClass {
	public static Application server;
	public static Application client;
	public static void init() throws Exception {
		if(server == null || client == null) {
		
		server = new ServerApplication("D:\\eclipse\\workspace\\emulator\\src\\test\\resources\\acceptor.txt");
		client = new ClientApplication("D:\\eclipse\\workspace\\emulator\\src\\test\\resources\\initiator.txt");
		
		server.start();
		client.start();
		}
//		FixMessage mes = new FixMessage(MessageFactory.createMessage("Fix4.2", "NewOrderSingle"));
//		client.send(mes, client.getSessions().get("BANZAI1"));
//		System.in.read();
		
//		server.stop();
//		client.stop();
	}
}