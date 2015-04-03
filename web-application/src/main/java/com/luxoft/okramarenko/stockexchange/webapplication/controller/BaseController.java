package com.luxoft.okramarenko.stockexchange.webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

//	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomes(ModelMap model) {
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
		return "index";
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
		return "table3";
	}
}