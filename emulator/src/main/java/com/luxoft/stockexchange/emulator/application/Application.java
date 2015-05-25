package com.luxoft.stockexchange.emulator.application;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import quickfix.ConfigError;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RuntimeError;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SessionSettings;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.entities.FixSession;
import com.luxoft.stockexchange.emulator.handlers.MessageHandler;

public abstract class Application implements MessageObserver {
	protected SessionSettings settings;
	protected MessageStoreFactory storeFactory;
	protected LogFactory logFactory;
	protected MessageFactory messageFactory;
	protected quickfix.Application app;
	protected MessageHandler handler;
	protected final Map<String, FixSession> sessions = new HashMap<>();
	
	public Application(String pathToConfig) {
		//TODO
		try {
			settings = new SessionSettings(pathToConfig);
		} catch (ConfigError e) {
			e.printStackTrace();
		}
	}
	
	public Application(String pathToConfig, MessageHandler handler, MessageStoreFactory storeFactory, LogFactory logFactory, MessageFactory messageFactory) {
		this(pathToConfig);
		
		this.handler = handler;
		this.storeFactory = storeFactory;
		this.logFactory = logFactory;
		this.messageFactory = messageFactory;
	}
	
	public abstract void start() throws RuntimeError, ConfigError;
	
	public abstract void stop();

	public void send(FixMessage message, FixSession session) throws IncorrectTagValue, FieldNotFound, SessionNotFound {
		handler.handle(message, session);
	}
	
	@Override
	public void updateIn(quickfix.Message message, SessionID sessionID) {
		FixSession session = getSessionBySessionID(sessionID);
		FixMessage fixMessage = new FixMessage(message);
		
		fixMessage.setProcessedTime(new Time(System.currentTimeMillis()));
		fixMessage.setSessionId(session.getId());
        fixMessage.setDirection(FixMessage.DIRECTION_IN);
		
		session.getSeqIn().add(fixMessage);
	}
	
	@Override
	public void updateOut(quickfix.Message message, SessionID sessionID) {
		FixSession session = getSessionBySessionID(sessionID);
		FixMessage fixMessage = new FixMessage(message);
		
		fixMessage.setProcessedTime(new Time(System.currentTimeMillis()));
		fixMessage.setSessionId(session.getId());
        fixMessage.setDirection(FixMessage.DIRECTION_OUT);
		
		session.getSeqOut().add(fixMessage);
	}
	
	public Map<String, FixSession> getSessions() {
		return sessions;
	}

	public MessageHandler getHandler() {
		return handler;
	}

	public Application setHandler(MessageHandler handler) {
		this.handler = handler;
		return this;
	}
	
	//TODO notify if there is no session
	private FixSession getSessionBySessionID(SessionID sessionID) {
		for(Entry<String, FixSession> entry : sessions.entrySet()) {
			if (entry.getValue().getSession().equals(sessionID)) { 
				return entry.getValue();
			}
		}
		
		return null;
	}

	public SessionSettings getSettings() {
		return settings;
	}
	
	public MessageStoreFactory getStoreFactory() {
		return storeFactory;
	}
	
	public LogFactory getLogFactory() {
		return logFactory;
	}
	
	public MessageFactory getMessageFactory() {
		return messageFactory;
	}
	
	public Application setStoreFactory(MessageStoreFactory storeFactory) {
		this.storeFactory = storeFactory;
		return this;
	}
	
	public Application setLogFactory(LogFactory logFactory) {
		this.logFactory = logFactory;
		return this;
	}
	
	public Application setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
		return this;
	}
}
