package com.luxoft.stockexchange.emulator.handlers;

import com.luxoft.stockexchange.emulator.entities.FixSession;

import java.io.IOException;

/**
 * Created by victorvorontsov on 02.06.15.
 */
public interface SessionHandler {

    abstract void start(FixSession session) throws IOException;

    abstract void stop(FixSession session) throws IOException;

    abstract FixSession.Status getStatus(FixSession session) throws IOException;

    abstract int getNextSenderMsgSeqNum(FixSession session) throws IOException;

    abstract int getNextTargetMsgSeqNum(FixSession session) throws IOException;

    abstract void setNextSenderMsgSeqNum(FixSession session, int var1) throws IOException;

    abstract void setNextTargetMsgSeqNum(FixSession session, int var1) throws IOException;

}
