package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String servers(ModelMap model) {
		try {
			InitClass.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("serverSessions", InitClass.server.getSessions());
		model.addAttribute("clientSessions", InitClass.client.getSessions());
		
		return "servers";
	}
	
	@RequestMapping(value = "/{session}", method = RequestMethod.GET)
	public String sessions(@PathVariable String session, ModelMap model) {
		
		model.addAttribute("session", InitClass.server.getSessions().get(session));
		model.addAttribute("name", session);
		
		return "sessions";
	}
	
	//TODO
	@RequestMapping(value = "/{session}/{message}", method = RequestMethod.GET)
	public String messages(@PathVariable String session, @PathVariable int message, ModelMap model) {
		
		Message mes = getMessage(InitClass.server.getSessions().get(session), message).getMessage();
		
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