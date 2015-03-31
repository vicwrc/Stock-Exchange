package com.luxoft.stockexchange.emulator.application;

import quickfix.ConfigError;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Initiator;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RejectLogon;
import quickfix.RuntimeError;
import quickfix.SessionID;
import quickfix.SocketInitiator;
import quickfix.UnsupportedMessageType;

import com.luxoft.stockexchange.emulator.entities.FixSession;
import com.luxoft.stockexchange.emulator.handlers.MessageHandler;

public final class ClientApplication extends Application {
	private Initiator initiator;

	public ClientApplication(String pathToConfig) {
		super(pathToConfig);
	}
	
	public ClientApplication(String pathToConfig, MessageHandler handler, MessageStoreFactory storeFactory, LogFactory logFactory, MessageFactory messageFactory) {
		super(pathToConfig, handler, storeFactory, logFactory, messageFactory);
	}

	@Override
	public void start() throws RuntimeError, ConfigError {
		app = new Client(this);
		// TODO
		try {
			initiator = new SocketInitiator(app, storeFactory, settings, logFactory, messageFactory);
		} catch (ConfigError e) {
			e.printStackTrace();
		}
		
		initiator.start();

		for (SessionID session : initiator.getSessions()) {
			sessions.put(session.getSenderCompID(), new FixSession(session));
		}
	}

	@Override
	public void stop() {
		initiator.stop();
	}

	// TODO 
	private static class Client extends quickfix.MessageCracker implements quickfix.Application {
		private MessageObserver observer;
		
		public Client(MessageObserver observer) {
			this.observer = observer;
		}
		
		@Override
		public void onCreate(SessionID paramSessionID) {

		}

		@Override
		public void onLogon(SessionID paramSessionID) {
			
		}

		@Override
		public void onLogout(SessionID paramSessionID) {
			
		}

		@Override
		public void toAdmin(quickfix.Message paramMessage, SessionID paramSessionID) {
			observer.updateOut(paramMessage, paramSessionID);
		}

		@Override
		public void fromAdmin(quickfix.Message paramMessage, SessionID paramSessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
				RejectLogon {
			observer.updateIn(paramMessage, paramSessionID);
		}

		@Override
		public void toApp(quickfix.Message paramMessage, SessionID paramSessionID) throws DoNotSend {
			observer.updateOut(paramMessage, paramSessionID);
		}

		@Override
		public void fromApp(quickfix.Message paramMessage, SessionID paramSessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue,
				UnsupportedMessageType {
			observer.updateIn(paramMessage, paramSessionID);
		}

	}
}
