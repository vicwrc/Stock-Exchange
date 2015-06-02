package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.luxoft.stockexchange.emulator.FixEmulator;
import com.luxoft.stockexchange.emulator.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import quickfix.Field;
import quickfix.FieldMap;
import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.Message;

import com.luxoft.stockexchange.emulator.entities.FixMessage;
import com.luxoft.stockexchange.emulator.entities.FixSession;

@Controller
public class BaseController {

    @Autowired
    private FixEmulator emulator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String servers(ModelMap model) {

		model.addAttribute("serverSessions", emulator.server.getSessions());
		model.addAttribute("clientSessions", emulator.client.getSessions());
		
		return "servers";
	}
	
	@RequestMapping(value = "/server/{session}", method = RequestMethod.GET)
	public String serverSessions(@PathVariable String session, ModelMap model) {
		
		model.addAttribute("session", emulator.server.getSessions().get(session));
		model.addAttribute("name", session);
        model.addAttribute("type", "server");
		
		return "sessions";
	}

    @RequestMapping(value = "/client/{session}", method = RequestMethod.GET)
    public String clientSessions(@PathVariable String session, ModelMap model) {

        model.addAttribute("session", emulator.client.getSessions().get(session));
        model.addAttribute("name", session);
        model.addAttribute("type", "client");

        return "sessions";
    }

    @RequestMapping(value = "/control/server/{session}", method = RequestMethod.GET)
    public String serverSessionControl(@PathVariable String session, ModelMap model) {

        model.addAttribute("session", emulator.server.getSessions().get(session));
        model.addAttribute("name", session);
        model.addAttribute("sessionHandler", emulator.server.getSessionHandler());
        model.addAttribute("type", "server");

        return "sessioncontrol";
    }

    @RequestMapping(value = "/control/client/{session}", method = RequestMethod.GET)
    public String clientSessionControl(@PathVariable String session, ModelMap model) {

        model.addAttribute("session", emulator.client.getSessions().get(session));
        model.addAttribute("sessionHandler", emulator.server.getSessionHandler());
        model.addAttribute("name", session);
        model.addAttribute("type", "client");

        return "sessioncontrol";
    }


    //TODO
	@RequestMapping(value = "/message/{emulatorType}/{session}/{message}", method = RequestMethod.GET)
	public String messages(@PathVariable String emulatorType, @PathVariable String session, @PathVariable int message, ModelMap model) {
		
		Message mes = getMessage(getApplicationByType(emulatorType).getSessions().get(session), message).getMessage();
		
		Map<Integer, String> map = new HashMap<>();
		
		try {
			map.putAll(fieldMapToMap(mes.getHeader()));
			map.putAll(fieldMapToMap(mes));
			map.putAll(fieldMapToMap(mes.getTrailer()));
		} catch (FieldNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        model.addAttribute("tags", map);
        model.addAttribute("name", session);
        
		return "message";
	}

    private Application getApplicationByType(String type) {
        if("server".equalsIgnoreCase(type)) {
            return emulator.server;
        } else {
            return emulator.client;
        }
    }
	
	//TODO
	private FixMessage getMessage(FixSession session, int id) {
		for (FixMessage message : session.getSeqIn()) {
			if (message.getId() == id) return message;
		}
		
		for (FixMessage message : session.getSeqOut()) {
			if (message.getId() == id) return message;
		}
		
		return null;
	}
	
	private Map<Integer, String> fieldMapToMap(FieldMap fieldMap) throws FieldNotFound {
		Map<Integer, String> result = new HashMap<>();

		Iterator<Field<?>> fieldIterator = fieldMap.iterator();

		while (fieldIterator.hasNext()) {
			Field<?> field = fieldIterator.next();
			String value = fieldMap.getString(field.getTag());
			result.put(field.getTag(), value);
		}

		Iterator<Integer> groupsKeys = fieldMap.groupKeyIterator();
		while (groupsKeys.hasNext()) {
			int groupCountTag = (groupsKeys.next()).intValue();

			result.put(groupCountTag, fieldMap.getInt(groupCountTag) + "");

			Group group = new Group(groupCountTag, 0);
			int i = 1;
			while (fieldMap.hasGroup(i, groupCountTag)) {
				fieldMap.getGroup(i, group);
				fieldMapToMap(group);
				i++;
			}
		}
		
		return result;
	}
}