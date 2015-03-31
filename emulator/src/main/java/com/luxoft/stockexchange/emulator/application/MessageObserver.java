package com.luxoft.stockexchange.emulator.application;

import quickfix.SessionID;


public interface MessageObserver {
	public void updateIn(quickfix.Message message, SessionID sessionID);
	
	public void updateOut(quickfix.Message message, SessionID sessionID);
}
