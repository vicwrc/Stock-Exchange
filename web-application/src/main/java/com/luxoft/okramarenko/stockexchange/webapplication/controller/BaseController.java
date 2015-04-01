package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionNotFound;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.utils.MessageFactory;

public class BaseController {
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String sendMessage(@RequestParam String action, ModelMap model) {
//		public String welcome(ModelMap model) {
		try {
			InitClass.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( action.equals("save") ){
		       //handle save
			FixMessage mes = null;
			try {
				mes = new FixMessage(MessageFactory.createMessage("Fix4.2", "NewOrderSingle"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				InitClass.client.send(mes, InitClass.client.getSessions().get("BANZAI1"));
			} catch (IncorrectTagValue | FieldNotFound | SessionNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\n\n\n\n BUTTON \n\n\n\n\n");
		    }
		
		model.addAttribute("serverSessions", InitClass.server.getSessions());
		model.addAttribute("clientSessions", InitClass.client.getSessions());
		
		

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
//		public String welcome(ModelMap model) {
		try {
			InitClass.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("serverSessions", InitClass.server.getSessions());
		model.addAttribute("clientSessions", InitClass.client.getSessions());
		
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;
	}
	

	/*@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}*/

}
