package com.luxoft.stockexchange.emulator.handlers;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionNotFound;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.entities.FixSession;

public interface MessageHandler {

	public abstract void handle(FixMessage message, FixSession session) throws IncorrectTagValue, SessionNotFound, FieldNotFound;

}
