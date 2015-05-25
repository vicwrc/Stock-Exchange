package com.luxoft.stockexchange.emulator.entities;

import java.sql.Time;

public class FixMessage {

    public static final String DIRECTION_IN = "IN";
    public static final String DIRECTION_OUT = "OUT";
	//TODO change to string random generator or ID from DB
	private static int idCounter = 0;
	private int id;
	private Time processedTime;
	private quickfix.Message message;
	private String direction;
	private int sessionId;
	
	public FixMessage(quickfix.Message message) {
		this.message = message;
		id = idCounter++;
		
		processedTime = new Time(System.currentTimeMillis());
	}
	
	public int getId() {
		return id;
	}
	
	public FixMessage setId(int id) {
		this.id = id;
		return this;
	}
	
	public Time getProcessedTime() {
		return processedTime;
	}
	
	public FixMessage setProcessedTime(Time processedTime) {
		this.processedTime = processedTime;
		return this;
	}
	
	public quickfix.Message getMessage() {
		return message;
	}
	
	public FixMessage setMessage(quickfix.Message message) {
		this.message = message;
		return this;
	}
	
	public int getSessionId() {
		return sessionId;
	}
	
	public FixMessage setSessionId(int sessionId) {
		this.sessionId = sessionId;
		return this;
	}

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("id: "  + id);
		str.append(", time: "  + processedTime);
		str.append(", sessionId: "  + sessionId);
		
		return str.toString();
	}

    public static FixMessage empty(){
        return new FixMessage(null);
    }
}
