package com.luxoft.stockexchange.emulator.handlers;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionNotFound;

import com.luxoft.stockexchange.emulator.entities.FixSession;
import com.luxoft.stockexchange.emulator.entities.FixMessage;

public class SimpleMessageHandler implements MessageHandler {

	@Override
	public void handle(FixMessage message, FixSession session) throws IncorrectTagValue, SessionNotFound, FieldNotFound {
		quickfix.Session.sendToTarget(message.getMessage(), session.getSession());
	}

}
