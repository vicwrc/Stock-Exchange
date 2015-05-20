package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import quickfix.DefaultMessageFactory;
import quickfix.FileStoreFactory;
import quickfix.ScreenLogFactory;

import com.luxoft.stockexchange.emulator.application.Application;
import com.luxoft.stockexchange.emulator.application.ClientApplication;
import com.luxoft.stockexchange.emulator.application.ServerApplication;
import com.luxoft.stockexchange.emulator.handlers.SimpleMessageHandler;

public final class InitClass {
	public static Application server;
	public static Application client;
	public static void init() throws Exception {
		if(server == null || client == null) {
		
		server = new ServerApplication("D:\\eclipse\\workspace\\Stock Exchange\\emulator\\src\\test\\resources\\acceptor.txt");
		server.setHandler(new SimpleMessageHandler());
		server.setLogFactory(new ScreenLogFactory());
		server.setMessageFactory(new DefaultMessageFactory());
		server.setStoreFactory(new FileStoreFactory(server.getSettings()));
		
		client = new ClientApplication("D:\\eclipse\\workspace\\Stock Exchange\\emulator\\src\\test\\resources\\initiator.txt");
		client.setHandler(new SimpleMessageHandler());
		client.setLogFactory(new ScreenLogFactory());
		client.setMessageFactory(new DefaultMessageFactory());
		client.setStoreFactory(new FileStoreFactory(server.getSettings()));
		
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