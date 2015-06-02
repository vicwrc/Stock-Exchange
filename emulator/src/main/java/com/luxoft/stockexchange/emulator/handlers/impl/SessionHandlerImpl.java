package com.luxoft.stockexchange.emulator.handlers.impl;

import com.luxoft.stockexchange.emulator.entities.FixSession;
import com.luxoft.stockexchange.emulator.handlers.SessionHandler;

import java.io.IOException;

/**
 * Created by victorvorontsov on 02.06.15.
 */
public class SessionHandlerImpl implements SessionHandler {

    @Override
    public void start(FixSession session) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        //fixSession.connect
    }

    @Override
    public void stop(FixSession session) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        fixSession.disconnect("Requested by user", true);
    }

    @Override
    public FixSession.Status getStatus(FixSession session) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        return fixSession.isEnabled() ? FixSession.Status.ACTIVE : FixSession.Status.STOPPED;
    }

    @Override
    public int getNextSenderMsgSeqNum(FixSession session) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        return fixSession.getExpectedSenderNum();
    }

    @Override
    public int getNextTargetMsgSeqNum(FixSession session) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        return fixSession.getExpectedTargetNum();
    }

    @Override
    public void setNextSenderMsgSeqNum(FixSession session, int var1) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        fixSession.setNextSenderMsgSeqNum(var1);
    }

    @Override
    public void setNextTargetMsgSeqNum(FixSession session, int var1) throws IOException {
        quickfix.Session fixSession = quickfix.Session.lookupSession(session.getSession());
        fixSession.setNextTargetMsgSeqNum(var1);
    }

}
