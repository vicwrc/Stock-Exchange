package com.luxoft.stockexchange.emulator.application;

import quickfix.Acceptor;
import quickfix.ConfigError;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.IntField;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.RejectLogon;
import quickfix.RuntimeError;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SocketAcceptor;
import quickfix.UnsupportedMessageType;
import quickfix.field.AvgPx;
import quickfix.field.ClOrdID;
import quickfix.field.CumQty;
import quickfix.field.ExecID;
import quickfix.field.ExecTransType;
import quickfix.field.ExecType;
import quickfix.field.LastPx;
import quickfix.field.LastQty;
import quickfix.field.LastShares;
import quickfix.field.LeavesQty;
import quickfix.field.OrdStatus;
import quickfix.field.OrdType;
import quickfix.field.OrderID;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.Text;

import com.luxoft.stockexchange.emulator.entities.FixSession;
import com.luxoft.stockexchange.emulator.handlers.MessageHandler;

public final class ServerApplication extends Application {
	private Acceptor acceptor;
	
	public ServerApplication(String pathToConfig) {
		super(pathToConfig);
	}
	
	public ServerApplication(String pathToConfig, MessageHandler handler, MessageStoreFactory storeFactory, LogFactory logFactory, MessageFactory messageFactory) {
		super(pathToConfig, handler, storeFactory, logFactory, messageFactory);
	}

	@Override
	public void start() throws RuntimeError, ConfigError {
		//TODO
		app = new Server(this);
		
		//TODO
		try {
			acceptor = new SocketAcceptor(app, storeFactory, settings, logFactory, messageFactory);
		} catch (ConfigError e) {
			e.printStackTrace();
		}
		
		acceptor.start();
		
		for (SessionID session : acceptor.getSessions()) {
			sessions.put(session.getTargetCompID(), new FixSession(session));
		}
	}

	@Override
	public void stop() {
		acceptor.stop();
	}

	//TODO
	private static class Server extends quickfix.MessageCracker implements quickfix.Application {
		private MessageObserver observer;
		
		public Server(MessageObserver observer) {
			this.observer = observer;
		}
	    
	    public void onCreate(SessionID sessionID) {
	    }
	 
	    public void onLogon(SessionID sessionID) {
	    }
	 
	    public void onLogout(SessionID sessionID) {
	    }
	 
	    public void toAdmin(quickfix.Message message, SessionID sessionID) {
	    		observer.updateOut(message, sessionID);
	    }
	 
	    public void toApp(quickfix.Message message, SessionID sessionID) throws DoNotSend {
	    	observer.updateOut(message, sessionID);
	    }
	 //TODO
	    public void fromAdmin(quickfix.Message message, SessionID sessionID) throws FieldNotFound,
	            IncorrectDataFormat, IncorrectTagValue, RejectLogon {
	    		observer.updateIn(message, sessionID);
	    }
	 
	    @Override
	    public void fromApp(quickfix.Message message, SessionID sessionID) throws FieldNotFound,
	            IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
	    	observer.updateIn(message, sessionID);
	        crack(message, sessionID);
	    }
	 
	    public void onMessage(quickfix.fix40.NewOrderSingle order, SessionID sessionID)
	            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	    	
	        Symbol symbol = new Symbol();
	        Side side = new Side();
	        OrdType ordType = new OrdType();
	        OrderQty orderQty = new OrderQty();
	        Price price = new Price();
	        ClOrdID clOrdID = new ClOrdID();
	 
	        order.get(ordType);
	 
	        order.get(symbol);
	        order.get(side);
	        order.get(orderQty);
	        order.get(price);
	        order.get(clOrdID);
	 
	        quickfix.fix40.ExecutionReport executionReport = new quickfix.fix40.ExecutionReport(
	                genOrderID(), genExecID(), new ExecTransType(ExecTransType.NEW), new OrdStatus(
	                        OrdStatus.FILLED), symbol, side, orderQty, new LastShares(orderQty
	                        .getValue()), new LastPx(price.getValue()),
	                new CumQty(orderQty.getValue()), new AvgPx(price.getValue()));
	 
	        executionReport.set(clOrdID);
	 
	        try {
	            Session.sendToTarget(executionReport, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	 
	    public void onMessage(quickfix.fix41.NewOrderSingle order, SessionID sessionID)
	            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	        Symbol symbol = new Symbol();
	        Side side = new Side();
	        OrdType ordType = new OrdType();
	        OrderQty orderQty = new OrderQty();
	        Price price = new Price();
	        ClOrdID clOrdID = new ClOrdID();
	 
	        order.get(ordType);
	 
	        order.get(symbol);
	        order.get(side);
	        order.get(orderQty);
	        order.get(price);
	        order.get(clOrdID);
	 
	        quickfix.fix41.ExecutionReport executionReport = new quickfix.fix41.ExecutionReport(
	                genOrderID(), genExecID(), new ExecTransType(ExecTransType.NEW), new ExecType(
	                        ExecType.FILL), new OrdStatus(OrdStatus.FILLED), symbol, side, orderQty,
	                new LastShares(orderQty.getValue()), new LastPx(price.getValue()),
	                new LeavesQty(0), new CumQty(orderQty.getValue()), new AvgPx(price.getValue()));
	 
	        executionReport.set(clOrdID);
	 
	        try {
	            Session.sendToTarget(executionReport, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	    
	    @Handler
	    public void onMessage(quickfix.fix42.OrderCancelRequest request, SessionID sessionID) {
	    	System.out.println("\n\n\n\nREJECT COME \n\n\n\n\n\n");
	    	
	    	quickfix.fix42.Reject reject = new quickfix.fix42.Reject();
	    	reject.setField(new IntField(45, 10));
	    	
	    	try {
	            Session.sendToTarget(reject, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	    
	    @Handler
	    public void onMessage(quickfix.fix42.NewOrderSingle order, SessionID sessionID)
	            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	    	System.out.println("\n\n\n\nMESSAGE COME \n\n\n\n\n\n");
	        Symbol symbol = new Symbol();
	        Side side = new Side();
	        OrdType ordType = new OrdType();
	        OrderQty orderQty = new OrderQty();
	        Price price = new Price();
	        ClOrdID clOrdID = new ClOrdID();
	        order.get(ordType);
	 
	        readFields(order, symbol, side, orderQty, price, clOrdID);
	 
	        quickfix.fix42.ExecutionReport executionReport = new quickfix.fix42.ExecutionReport(
	                genOrderID(), genExecID(), new ExecTransType(ExecTransType.NEW), new ExecType(
	                        ExecType.FILL), new OrdStatus(OrdStatus.FILLED), symbol, side,
	                new LeavesQty(0), new CumQty(orderQty.getValue()), new AvgPx(price.getValue()));
	 
	        executionReport.set(clOrdID);
	        executionReport.set(orderQty);
	        executionReport.set(new Text("RECEIVE BACK IT"));
	        executionReport.set(new LastShares(orderQty.getValue()));
	        executionReport.set(new LastPx(price.getValue()));
	 
	        try {
	            Session.sendToTarget(executionReport, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	 
	    private void readFields(quickfix.fix42.NewOrderSingle order, Symbol symbol, Side side, OrderQty orderQty, Price price, ClOrdID clOrdID) throws FieldNotFound {
	        order.get(symbol);
	        order.get(side);
	        order.get(orderQty);
            order.get(price);
	        order.get(clOrdID);
	    }
	 
	    public void onMessage(quickfix.fix43.NewOrderSingle order, SessionID sessionID)
	            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	        Symbol symbol = new Symbol();
	        Side side = new Side();
	        OrdType ordType = new OrdType();
	        OrderQty orderQty = new OrderQty();
	        Price price = new Price();
	        ClOrdID clOrdID = new ClOrdID();
	 
	        order.get(ordType);
	 
	        order.get(symbol);
	        order.get(side);
	        order.get(orderQty);
	        order.get(price);
	        order.get(clOrdID);
	 
	        quickfix.fix43.ExecutionReport executionReport = new quickfix.fix43.ExecutionReport(
	                genOrderID(), genExecID(), new ExecType(ExecType.FILL), new OrdStatus(
	                        OrdStatus.FILLED), side, new LeavesQty(0), new CumQty(orderQty.getValue()),
	                new AvgPx(price.getValue()));
	 
	        executionReport.set(clOrdID);
	        executionReport.set(symbol);
	        executionReport.set(orderQty);
	        executionReport.set(new LastQty(orderQty.getValue()));
	        executionReport.set(new LastPx(price.getValue()));
	 
	        try {
	            Session.sendToTarget(executionReport, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	 
	    public void onMessage(quickfix.fix44.NewOrderSingle order, SessionID sessionID)
	            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
	        Symbol symbol = new Symbol();
	        Side side = new Side();
	        OrdType ordType = new OrdType();
	        OrderQty orderQty = new OrderQty();
	        Price price = new Price();
	        ClOrdID clOrdID = new ClOrdID();
	 
	        order.get(ordType);
	 
	        order.get(symbol);
	        order.get(side);
	        order.get(orderQty);
	        order.get(price);
	        order.get(clOrdID);
	 
	        quickfix.fix44.ExecutionReport executionReport = new quickfix.fix44.ExecutionReport(
	                genOrderID(), genExecID(), new ExecType(ExecType.FILL), new OrdStatus(
	                        OrdStatus.FILLED), side, new LeavesQty(0), new CumQty(orderQty.getValue()),
	                new AvgPx(price.getValue()));
	 
	        executionReport.set(clOrdID);
	        executionReport.set(symbol);
	        executionReport.set(orderQty);
	        executionReport.set(new LastQty(orderQty.getValue()));
	        executionReport.set(new LastPx(price.getValue()));
	 
	        try {
	            Session.sendToTarget(executionReport, sessionID);
	        } catch (SessionNotFound e) {
	        }
	    }
	 
	    public OrderID genOrderID() {
	        return new OrderID(new Integer(++m_orderID).toString());
	    }
	 
	    public ExecID genExecID() {
	        return new ExecID(new Integer(++m_execID).toString());
	    }
	 
	    private int m_orderID = 0;
	    private int m_execID = 0;

	}
}
